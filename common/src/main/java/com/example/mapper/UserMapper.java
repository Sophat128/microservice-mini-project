package com.example.mapper;

import com.example.entity.User;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.UUID;

public class UserMapper {

	public static User toDto(UserRepresentation userRepresentation) {
		User user = new User();
		user.setId(UUID.fromString(userRepresentation.getId()));
		user.setUsername(userRepresentation.getUsername());
		user.setEmail(userRepresentation.getEmail());
		user.setFirstName(userRepresentation.getFirstName());
		user.setLastName(userRepresentation.getLastName());
		return user;
	}

	public static UserRepresentation toRepresentation(User user) {
		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setId(user.getId().toString());
		userRepresentation.setUsername(user.getUsername());
		userRepresentation.setEmail(user.getEmail());
		userRepresentation.setFirstName(user.getFirstName());
		userRepresentation.setLastName(user.getLastName());
		return userRepresentation;
	}

}
