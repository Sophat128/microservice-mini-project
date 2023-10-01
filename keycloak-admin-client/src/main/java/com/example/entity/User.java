package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private UUID id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
}