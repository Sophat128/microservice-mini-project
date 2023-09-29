package com.example.dto;

import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public class GroupUserDto {
	private GroupRepresentation group;
	private UserRepresentation user;

	public GroupUserDto(GroupRepresentation group, UserRepresentation user) {
		this.group = group;
		this.user = user;
	}
}
