package com.example.model.request;


import com.example.model.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    private String title;
    private String description;
    private String createBy;
    private String assignTo;
    private String groupId;

    public Task toEntity(){
        return new Task(null, title, description, createBy, assignTo, groupId);
    }
}
