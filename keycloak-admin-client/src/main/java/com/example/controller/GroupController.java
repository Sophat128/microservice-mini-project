package com.example.controller;

import com.example.model.respone.ApiResponse;
import com.example.service.GroupService;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/groups")
@AllArgsConstructor
public class GroupController {


    private final GroupService groupService;
    @GetMapping
    public ResponseEntity<?> getAllGroup() {
        ApiResponse<?> response = ApiResponse.builder()
                .message("insert user successfully")
                .status("200")
                .payload(groupService.getAllGroups())
                .build();
        return ResponseEntity.ok(response);
    }



}
