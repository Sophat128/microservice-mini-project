package com.example.model.entity;

import com.example.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MyUser {
    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;


    public UserDto toDto() {
        return new UserDto(
                this.id,
                this.username,
                this.firstName,
                this.lastName,
                this.email

        );

    }

    ;
}

