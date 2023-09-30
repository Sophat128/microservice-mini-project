package com.example.controller;



import com.example.entity.User;
import com.example.request.UserRequest;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<User> getAllUser() {
		return userService.getAllUsers();
	}


	@GetMapping("/users/search")
	public ResponseEntity<List<User>> getAllUser(@RequestParam String username) {
		List<User> users = userService.findByUsername(username);

		if (users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(users);
		}

		return ResponseEntity.ok(users);
	}


	@PostMapping("/users")
	public ResponseEntity<String> addUser(@RequestBody UserRequest request) {
		try {
			if (request.getUsername() == null || request.getUsername().isBlank()) {
				return ResponseEntity.badRequest().body("Username cannot be blank");
			}

			if (request.getPassword() == null || request.getPassword().isEmpty()) {
				return ResponseEntity.badRequest().body("Password cannot be empty");
			}
			if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				return ResponseEntity.badRequest().body("Invalid email format");
			}
			if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
				return ResponseEntity.badRequest().body("FirstName cannot be empty");
			}
			if (request.getLastName() == null || request.getLastName().isEmpty()) {
				return ResponseEntity.badRequest().body("LastName cannot be empty");
			}
			userService.addUser(request);

			return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user");
		}
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable String userId) {
		try {
			userService.deleteUserById(userId);
			return ResponseEntity.ok("User deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user with incorrect id");
		}
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		try {
			User user = userService.getUserById(userId);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}


	}


	@PutMapping("/users/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) {
		try {
			if (userRequest.getUsername() == null || userRequest.getUsername().isBlank()) {
				return ResponseEntity.badRequest().body("Username cannot be blank");
			}
			if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) {
				return ResponseEntity.badRequest().body("Password cannot be empty");
			}
			if (userRequest.getEmail() == null || !userRequest.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				return ResponseEntity.badRequest().body("Invalid email format");
			}
			if (userRequest.getFirstName() == null || userRequest.getFirstName().isEmpty()) {
				return ResponseEntity.badRequest().body("FirstName cannot be empty");
			}
			if (userRequest.getLastName() == null || userRequest.getLastName().isEmpty()) {
				return ResponseEntity.badRequest().body("LastName cannot be empty");
			}
			userService.updateUserById(userId, userRequest);
			return ResponseEntity.ok("User updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user with incorrect id");
		}
	}

	@GetMapping("/users/getByEmail")
	public ResponseEntity<List<User>> getUserByEmail(@RequestParam String email) {

		List<User> users = userService.getUserByEmail(email);

		if (users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(users);
		}

		return ResponseEntity.ok(users);
	}

}
