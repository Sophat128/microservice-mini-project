package com.example.controller;
import com.example.model.respone.ApiResponse;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {


    private final UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllUser() {
        ApiResponse<?> response = ApiResponse.builder()
                .message("insert user successfully")
                .status("200")
                .payload(userService.getAllUsers())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAllUser(@RequestParam String username) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("insert user successfully")
                .status("200")
                .payload(userService.findByUsername(username))
                .build();
        return ResponseEntity.ok(response);
    }


}
