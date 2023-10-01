//package com.example;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.web.client.RestOperations;
//
//import java.time.Duration;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//
//public class ResourceServerSecurityConfig {
//    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
//    private String jwkSetUri;
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
////    @Bean
////    public JwtDecoder jwtDecoder() {
////        RestTemplateBuilder builder = new RestTemplateBuilder();
////        RestOperations rest = builder
////                .setConnectTimeout(Duration.ofSeconds(10))
////                .setReadTimeout(Duration.ofSeconds(10))
////                .build();
////
////        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).restOperations(rest).build();
////    }
//
//
//
//
//
//}
