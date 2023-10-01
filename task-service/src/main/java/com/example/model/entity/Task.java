package com.example.model.entity;

import com.example.entity.Group;
import com.example.entity.User;
import com.example.model.dto.TaskDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String title;
    private String description;
    private String createBy;
    private String assignTo;
    private String groupId;

    public TaskDto toDto(User createdBy, User assignTo, Group group) {
        return new TaskDto(
                this.title,
                this.description,
                createdBy,
                assignTo,
                group
        );
    }


}
