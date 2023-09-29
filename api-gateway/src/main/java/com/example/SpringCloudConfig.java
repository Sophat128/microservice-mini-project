package com.example;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator buildRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/task-service/v3/api-docs").and().method(HttpMethod.GET).uri("lb://task-service"))
                .route(r -> r.path("/keycloak-client/v3/api-docs").and().method(HttpMethod.GET).uri("lb://keycloak-client"))
                .build();
    }


}
