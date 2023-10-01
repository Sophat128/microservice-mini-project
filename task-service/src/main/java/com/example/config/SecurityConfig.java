package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestOperations;

import java.time.Duration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String jwkSetUri;
	@Bean
	public SecurityFilterChain filterChain(
			HttpSecurity http
	) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);
		http.authorizeHttpRequests(authorize ->
				authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
						.requestMatchers("api/v1/users/users/**").permitAll()
						.requestMatchers("api/v1/groups/groups/**").permitAll()
						.anyRequest().authenticated()
		);
		http.oauth2ResourceServer(t -> {
			t.jwt(jwtConfigurer -> jwtDecoder());
		});
		http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
	@Bean
	public JwtDecoder jwtDecoder() {
		RestTemplateBuilder builder = new RestTemplateBuilder();
		RestOperations rest = builder
				.setConnectTimeout(Duration.ofSeconds(10))
				.setReadTimeout(Duration.ofSeconds(10))
				.build();

		return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).restOperations(rest).build();
	}





}
