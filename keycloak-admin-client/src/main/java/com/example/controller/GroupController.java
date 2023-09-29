package com.example.controller;

import com.example.entity.Group;
import com.example.service.GroupService;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

	private final GroupService groupService;
	private final UserService userService;


	public GroupController(GroupService groupService, UserService userService) {
		this.groupService = groupService;
		this.userService = userService;
	}

	@GetMapping("/groups")
	public List<Group> getAllUser() {
		return groupService.getAllGroups();
	}

	@GetMapping("/groups/{groupId}")
	public ResponseEntity<Group> getGroupById(@PathVariable String groupId) {
		Group group = groupService.getGroupById(groupId);
		if (group != null) {
			return ResponseEntity.ok(group);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/groups")
	public ResponseEntity<String> addNewGroup(@RequestBody Group group) {
		try {
			groupService.addNewGroup(group);
			return ResponseEntity.ok("Group added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add group");
		}
	}

	@DeleteMapping("/groups/{groupId}")
	public ResponseEntity<String> deleteGroup(@PathVariable String groupId) {
		try {
			groupService.deleteGroupById(groupId);
			return ResponseEntity.ok("Group deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete group");
		}
	}

	@PostMapping("/groups/addUserToGroup")
	public ResponseEntity<String> addUserToGroup(
			@RequestParam String userId,
			@RequestParam String groupId) {
		 groupService.addUserToGroup(userId, groupId);

		if (true) {
			return ResponseEntity.ok("User added to group successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user to group");
		}
	}

	@GetMapping("/groups/{groupId}/users")
	public ResponseEntity<?> getUserAndGroupById(@PathVariable String groupId) {
		return ResponseEntity.ok(groupService.getGroupAndUserById(groupId));
	}
}
