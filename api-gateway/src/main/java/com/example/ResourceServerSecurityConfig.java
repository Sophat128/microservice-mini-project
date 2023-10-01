//package com.example;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//
//public class ResourceServerSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(
//            HttpSecurity http
//    ) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(authorize ->
//                authorize
//                        .requestMatchers(HttpMethod.GET,
//                                "/actuator/health/**",
//                                "/webjars/**",
//                                "/swagger-ui.html",
//                                "/swagger-resources/**",
//                                "/v3/api-docs/**",
//                                "/keycloak-client/v3/api-docs/**",
//                                "/task-service/v3/api-docs/**",
//                                "/gateway-service/v3/api-docs/**"
//
//                        ).permitAll()
//                        .anyRequest().authenticated()
//        );
//        http.oauth2ResourceServer(t -> {
//            t.jwt(withDefaults());
//        });
//        http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return http.build();
//    }
//
//
//
//
//
//}
