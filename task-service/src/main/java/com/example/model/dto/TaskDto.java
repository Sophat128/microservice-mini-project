package com.example.model.dto;

import com.example.dto.GroupDto;
import com.example.dto.UserDto;
import com.example.entity.Group;
import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private User createdBy;
    private User assignTo;
    private Group group;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public TaskDto(String title, String description, User createdBy, User assignTo, Group group) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.assignTo = assignTo;
        this.group =group;
        LocalDateTime.now();
        LocalDateTime.now();
    }


}
