package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {

	private UUID groupId;
	private String name;

	private UserDto userDto;


	public GroupDto(UUID groupId, String name) {
		this.groupId=groupId;
		this.name=name;
	}


}
