package com.example.controller;

import com.example.exception.NotFoundExceptionClass;
import com.example.model.dto.TaskDto;
import com.example.model.request.TaskRequest;
import com.example.model.respone.ApiResponse;
import com.example.service.TaskService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@SecurityRequirement(name = "security_task_auth")
@CrossOrigin
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;


    @GetMapping
    @Retry(name = "task-service",fallbackMethod = "getAllAvailableTasks")
    public ResponseEntity<?> getAllTasks() {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get tasks successfully")
                .status("200")
                .payload(taskService.getAllTasks())
                .build();
        return ResponseEntity.ok(response);
    }
    public List<TaskDto> getAllAvailableTasks(Exception e){
        return Stream.of(
                new TaskDto(null, null, null,null,null,null),
                new TaskDto(null, null, null,null,null,null),
                new TaskDto(null, null, null,null,null,null)
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable UUID id) {
        try {
            ApiResponse<?> response = ApiResponse.builder()
                    .message("Get task successfully")
                    .status("200")
                    .payload(taskService.getTaskById(id))
                    .build();
            return ResponseEntity.ok(response);
        } catch (
                NotFoundExceptionClass e) {
            ApiResponse<?> response = ApiResponse.builder()
                    .message(e.getMessage())
                    .status("500")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody TaskRequest taskRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("add task successfully")
                .status("200")
                .payload(taskService.addTask(taskRequest))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable UUID id) {
        try {
            taskService.deleteTask(id);
            ApiResponse<?> response = ApiResponse.builder()
                    .message("delete task successfully")
                    .status("200")
                    .payload("Delete task successfully")
                    .build();
            return ResponseEntity.ok(response);
        } catch (
                EntityNotFoundException e) {
            ApiResponse<?> response = ApiResponse.builder()
                    .message("can't delete this task id::" + id)
                    .status("500")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable UUID id, @RequestBody TaskRequest taskRequest) {
        try {
            ApiResponse<?> response = ApiResponse.builder()
                    .message("update task successfully")
                    .status("200")
                    .payload(taskService.updateTask(id, taskRequest))
                    .build();
            return ResponseEntity.ok(response);
        } catch (
                NotFoundExceptionClass e) {
            ApiResponse<?> response = ApiResponse.builder()
                    .message("can't update this task id::" + id)
                    .status("500")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }


}
