package com.marketplace.productservice;
import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.web.SecurityFilterChain;
@Configuration class SecurityConfig{@Bean SecurityFilterChain security(HttpSecurity http)throws Exception{return http.csrf(c->c.disable()).authorizeHttpRequests(a->a.requestMatchers("/actuator/health","/swagger-ui/**","/v3/api-docs/**").permitAll().anyRequest().authenticated()).httpBasic(b->{}).build();}}
