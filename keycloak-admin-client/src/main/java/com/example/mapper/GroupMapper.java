package com.example.mapper;

import com.example.entity.Group;
import org.keycloak.representations.idm.GroupRepresentation;

import java.util.UUID;

public class GroupMapper {

	public static Group toDto(GroupRepresentation groupRepresentation){
		Group group= new Group();

		group.setGroupId(UUID.fromString(groupRepresentation.getId()));
		group.setName(groupRepresentation.getName());
		return group;
	}

	public static GroupRepresentation toRepresentation(Group group) {
		GroupRepresentation groupRepresentation = new GroupRepresentation();
		groupRepresentation.setId(group.getGroupId().toString());
		groupRepresentation.setName(group.getName());

		return groupRepresentation;
	}
}
