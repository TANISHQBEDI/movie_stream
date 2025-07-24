package com.backend.iffatv.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain basicAuthSecurityFilterChain(HttpSecurity http) throws Exception {

        return http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(request->{
//                  request.requestMatchers("/api/v1/**").permitAll();
                  request.requestMatchers("/api/v1/movies/*/watch").authenticated();
                  request.anyRequest().permitAll();
                })
                .csrf().disable()
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();


    }
}
