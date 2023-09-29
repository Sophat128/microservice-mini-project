package com.example.controller;



import com.example.entity.User;
import com.example.request.UserRequest;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public List<User> getAllUser(@RequestParam String username) {
		return userService.findByUsername(username);
	}


	@PostMapping("/users")
	public ResponseEntity<?> addNewUser(@RequestBody UserRequest userRequest) {
		try {
			userService.addUser(userRequest);
			return ResponseEntity.ok("User added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user");
		}
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable String userId) {
		try {
			userService.deleteUserById(userId);
			return ResponseEntity.ok("User deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
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
	public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody User user) {
		try {
			userService.updateUserById(userId, user);
			return ResponseEntity.ok("User updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user");
		}
	}

	@GetMapping("/users/getByEmail")
	public ResponseEntity<List<User>> getUserByEmail(@RequestParam String email) {
		try {
			List<User> users = userService.getUserByEmail(email);
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
