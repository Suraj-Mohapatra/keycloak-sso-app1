package com.uglyeagle.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	private final ClientRegistrationRepository clientRegistrationRepository;

	// Spring injects the ClientRegistrationRepository automatically
	public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
		this.clientRegistrationRepository = clientRegistrationRepository;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/public/**").permitAll().anyRequest().authenticated())

				.oauth2Login(Customizer.withDefaults())

				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

		// OIDC logout handler
		OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(
				clientRegistrationRepository);

		oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/keycloak-sso-app1/");

		http.logout(logout -> logout.logoutUrl("/logout") // end point to hit
				.logoutSuccessHandler(oidcLogoutSuccessHandler));

		return http.build();
	}
}
