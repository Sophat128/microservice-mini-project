package com.example.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;



}
