package com.example.service;

import com.example.model.dto.UserMapper;
import com.example.model.entity.Group;
import com.example.model.entity.MyUser;
import org.keycloak.admin.client.Keycloak;
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
        RealmResource realmResource = keycloak.realm(realm);
        GroupsResource groupsResource = realmResource.groups();
        return groupsResource.
                groups().stream().map(groupRepresentation ->
                        new Group(groupRepresentation.getId(), groupRepresentation.getName())).collect(Collectors.toList());
    }

}