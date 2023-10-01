package com.example.service.serviceImpl;

import com.example.dto.GroupDto;
import com.example.dto.UserDto;
import com.example.entity.Group;
import com.example.entity.User;
import com.example.exception.NotFoundExceptionClass;
import com.example.model.dto.TaskDto;
import com.example.model.entity.Task;
import com.example.model.request.TaskRequest;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final WebClient.Builder webClient;
    private final TaskRepository taskRepository;

    public Mono<User> getUserById(String userId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return webClient.baseUrl("http://keycloak-client/api/v1/users/users/")
                .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .build()
                .get()
                .uri("{id}", userId)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<Group> getGroupById(String groupId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("Token: " + jwt.getTokenValue());

        return webClient.baseUrl("http://keycloak-client/api/v1/groups/groups/")
                .defaultHeaders(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .build()
                .get()
                .uri("{id}", groupId)
                .retrieve()
                .bodyToMono(Group.class);
    }

    @Override
    public TaskDto addTask(TaskRequest taskRequest) {
        var task = taskRequest.toEntity();
        User createdBy = getUserById(taskRequest.getCreateBy()).block();
        User assignTo = getUserById(taskRequest.getAssignTo()).block();
        Group group = getGroupById(taskRequest.getGroupId()).block();
        return taskRepository.save(task).toDto(createdBy, assignTo, group);

//        return new TaskDto(taskRequest.getTitle(), taskRequest.getDescription(),createdBy,assignTo,group);
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.findById(id).orElseThrow(() -> new NotFoundExceptionClass("This task doesn't exist!"));
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto getTaskById(UUID id) {
        return taskRepository.findById(id).map(task -> {
                            User createdBy = getUserById(task.getCreateBy()).block();
                            User assignTo = getUserById(task.getAssignTo()).block();
                            Group group = getGroupById(task.getGroupId()).block();
                            return task.toDto(createdBy, assignTo, group);
                        }
                )
                .orElseThrow(() -> new NotFoundExceptionClass("This task doesn't exist!"));
    }

    @Override
    public TaskDto updateTask(UUID id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionClass("This task doesn't exist!"));
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCreateBy(taskRequest.getCreateBy());
        task.setAssignTo(taskRequest.getAssignTo());
        task.setGroupId(task.getGroupId());
        User createdBy = getUserById(task.getCreateBy()).block();
        User assignTo = getUserById(task.getAssignTo()).block();
        Group group = getGroupById(task.getGroupId()).block();

        return taskRepository.save(task).toDto(createdBy, assignTo, group);

    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(task -> {
            User createdBy = getUserById(task.getCreateBy()).block();
            User assignTo = getUserById(task.getAssignTo()).block();
            Group group = getGroupById(task.getGroupId()).block();
            return new TaskDto(task.getId(),task.getTitle(), task.getDescription(), createdBy, assignTo, group);
        }).toList();

    }
}
