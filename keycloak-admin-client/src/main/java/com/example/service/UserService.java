package com.example.service;


import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

	private final Keycloak keycloak;
	@Value("${keycloak.realm}")
	private String realm;
	public UserService(Keycloak keycloak) {
		this.keycloak = keycloak;
	}

	public List<User> getAllUsers() {
		List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().list();
		return userRepresentations.stream()
				.map(UserMapper::toDto)
				.collect(Collectors.toList());
	}

	public List<User> findByUsername(String username) {
		List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().search(username);
		return userRepresentations.stream()
				.map(UserMapper::toDto)
				.collect(Collectors.toList());
	}

	public User getUserById(String userId) {
		UserRepresentation userRepresentation = keycloak.realm(realm).users().get(userId).toRepresentation();
		return UserMapper.toDto(userRepresentation);
	}

	public List<User> getUserByEmail(String email) {
		try {
			UsersResource usersResource = keycloak.realm(realm).users();
			List<UserRepresentation> userRepresentations = usersResource.searchByEmail(email, false);

			if (userRepresentations != null && !userRepresentations.isEmpty()) {
				return userRepresentations.stream().map(UserMapper::toDto).toList(); // Assuming email is unique
			} else {
				return null; // User not found
			}
		} catch (Exception e) {
			// Handle exceptions as needed
			return null;
		}
	}


	public void addUser(User user) {
		UserRepresentation userRepresentation = UserMapper.toRepresentation(user);
		keycloak.realm(realm).users().create(userRepresentation);
	}

	public void deleteUserById(String userId) {
		keycloak.realm(realm).users().delete(userId);
	}

	public void updateUserById(String userId, User user) {
		UserRepresentation userRepresentation = UserMapper.toRepresentation(user);
		keycloak.realm(realm).users().get(userId).update(userRepresentation);
	}


}
