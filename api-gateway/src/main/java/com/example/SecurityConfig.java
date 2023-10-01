package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestOperations;

import java.time.Duration;

@Configuration
@EnableWebFluxSecurity
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
                                .anyRequest().authenticated()
        );
        http.
                oauth2Login(
                        Customizer.withDefaults());
        http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
//	@Bean
//	public JwtDecoder jwtDecoder() {
//		RestTemplateBuilder builder = new RestTemplateBuilder();
//		RestOperations rest = builder
//				.setConnectTimeout(Duration.ofSeconds(10))
//				.setReadTimeout(Duration.ofSeconds(10))
//				.build();
//
//		return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).restOperations(rest).build();
//	}
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	http.securityMatcher("/api/**").authorizeHttpRequests(rmr -> rmr
//			.requestMatchers("/api/admin/**").permitAll()
//			.anyRequest().authenticated()
//	);
//	http
//			.oauth2Login(Customizer.withDefaults())
//	).sessionManagement(smc -> smc
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	).csrf(AbstractHttpConfigurer::disable);
//	return http.build();
//}


}
