package com.clover;

import javax.servlet.FilterChain;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;

import com.clover.log.api.impl.DecompressRequestBodyFilter;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@Configuration
@EnableWebSecurity
public class CloverConfig {

	@Bean
	FilterRegistrationBean<MDCInsertingServletFilter> mdcInsertingServletFilter() {
		FilterRegistrationBean<MDCInsertingServletFilter> registration = new FilterRegistrationBean<>();
		MDCInsertingServletFilter mdcInsertingServletFilter = new MDCInsertingServletFilter();
		registration.setFilter(mdcInsertingServletFilter );
		registration.setOrder(1);
		registration.addUrlPatterns("/*");
		
		return registration;
	}
	
	@Bean
	FilterRegistrationBean<DecompressRequestBodyFilter> decompressRequestBodyFilter() {
		FilterRegistrationBean<DecompressRequestBodyFilter> registration = new FilterRegistrationBean<>();
		DecompressRequestBodyFilter decompressRequestBodyFilter = new DecompressRequestBodyFilter();
		registration.setFilter(decompressRequestBodyFilter );
		registration.setOrder(2);
		registration.addUrlPatterns("/*");
		
		return registration;
		
		
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests().anyRequest()
			.authenticated()
			.and()
			.csrf().disable()
			.oauth2ResourceServer().jwt();
		return http.build();
	}

}
