package com.example.model.dto;

import com.example.model.entity.MyUser;
import org.keycloak.representations.idm.UserRepresentation;
import java.util.UUID;

public class UserMapper {

    public static MyUser toDto(UserRepresentation userRepresentation) {
        MyUser user = new MyUser();
        user.setId(UUID.fromString(userRepresentation.getId()));
        user.setUsername(userRepresentation.getUsername());
        user.setEmail(userRepresentation.getEmail());
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());

        return user;
    }
}