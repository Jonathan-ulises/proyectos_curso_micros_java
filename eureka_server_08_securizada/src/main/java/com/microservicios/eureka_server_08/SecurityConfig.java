package com.microservicios.eureka_server_08;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetails() throws Exception {
        List<UserDetails> users = List.of(
            User
            .withUsername("admin")
            .password("{noop}admin")
            .roles("USERS", "ADMIN")
            .build()
            );

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(cus -> cus.disable())
            .authorizeHttpRequests(auth -> 
                auth.anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
