package com.example.service;


import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.request.UserRequest;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
	private static CredentialRepresentation createPasswordCredentials(String password) {
		CredentialRepresentation passwordCredentials = new CredentialRepresentation();
		passwordCredentials.setTemporary(false);
		passwordCredentials.setType(CredentialRepresentation.PASSWORD);
		passwordCredentials.setValue(password);
		return passwordCredentials;
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


	public void addUser(UserRequest request) {
		UsersResource usersResource = keycloak.realm(realm).users();
		CredentialRepresentation credentialRepresentation = createPasswordCredentials(request.getPassword());
		UserRepresentation userPre = new UserRepresentation();
		userPre.setUsername(request.getUsername());
		userPre.setCredentials(Collections.singletonList(credentialRepresentation));
		userPre.setFirstName(request.getFirstName());
		userPre.setLastName(request.getLastName());
		userPre.setEmail(request.getEmail());
		usersResource.create(userPre);

	}


		public void deleteUserById(String userId) {
		keycloak.realm(realm).users().delete(userId);
	}

	public void updateUserById(String userId, UserRequest request) {
		UsersResource usersResource = keycloak.realm(realm).users();
		UserResource userResource = usersResource.get(userId);

		UserRepresentation userRepresentation = userResource.toRepresentation();
		userRepresentation.setUsername(request.getUsername());
		userRepresentation.setFirstName(request.getFirstName());
		userRepresentation.setLastName(request.getLastName());
		userRepresentation.setEmail(request.getEmail());

		// Update the user representation
		userResource.update(userRepresentation);
	}



}
