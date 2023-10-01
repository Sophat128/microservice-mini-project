package com.example.service;

import com.example.entity.Group;
import com.example.entity.User;
import com.example.model.dto.TaskDto;
import com.example.model.entity.Task;
import com.example.model.request.TaskRequest;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Mono<User> getUserById(String userId);
    List<TaskDto> getAllTasks();
    Mono<Group> getGroupById(String groupId);

    TaskDto addTask(TaskRequest taskRequest);

    void deleteTask(UUID id);

    TaskDto getTaskById(UUID id);

    TaskDto updateTask(UUID id, TaskRequest taskRequest);
}
