package com.example.service;


import com.example.entity.Group;
import com.example.entity.User;
import com.example.mapper.GroupMapper;
import com.example.mapper.UserMapper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
	private final Keycloak keycloak;
	@Value("${keycloak.realm}")
	private String realm;

	public GroupService(Keycloak keycloak) {
		this.keycloak = keycloak;
	}

	public List<Group> getAllGroups() {
		List<GroupRepresentation> groupRepresentations = keycloak.realm(realm).groups().groups();
		return groupRepresentations.stream()
				.map(GroupMapper::toDto)
				.collect(Collectors.toList());

	}

	public Group getGroupById(String groupId) {
		GroupRepresentation groupRepresentation = keycloak.realm(realm).groups().group(groupId).toRepresentation();
		return GroupMapper.toDto(groupRepresentation);
	}

	public void addNewGroup(Group group) {
		GroupRepresentation groupRepresentation = new GroupRepresentation();
		groupRepresentation.setName(group.getName());
		keycloak.realm(realm).groups().add(groupRepresentation);
	}

	public void deleteGroupById(String groupId) {
		keycloak.realm(realm).groups().group(groupId).remove();
	}

	public void addUserToGroup(String userId, String groupId) {

		RealmResource realmResourceesource= keycloak.realm(realm);
		realmResourceesource.users().get(userId).joinGroup(String.valueOf(groupId));
	}

	public List<User> getGroupAndUserById(String groupId) {
		RealmResource realmResource = keycloak.realm(realm);
		GroupsResource groupsResource = realmResource.groups();
		GroupResource groupResource = groupsResource.group(groupId);
		List<UserRepresentation> groupRepresentation = groupResource.members();
		return groupRepresentation.stream().map(UserMapper::toDto).collect(Collectors.toList());
	}







}
