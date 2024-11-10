package com.microservicios.microservicio_contacto_04;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // @Bean
    // public InMemoryUserDetailsManager usersDetails() throws Exception {
    //     List<UserDetails> users = List.of(
    //         User
    //         .withUsername("user1")
    //         .password("{noop}user1")
    //         .roles("USERS")
    //         .build(),
    //         User
    //         .withUsername("user2")
    //         .password("{noop}user2")
    //         .roles("OPERATOR")
    //         .build(),
    //         User
    //         .withUsername("admin")
    //         .password("{noop}admin")
    //         .roles("USERS", "ADMIN")
    //         .build()
    //         );

    //     return new InMemoryUserDetailsManager(users);
    // }


    @Bean
    public JdbcUserDetailsManager userDetailsJdbc() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/springsecurity?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("root");

        JdbcUserDetailsManager jdbcDetails = new JdbcUserDetailsManager(ds);
        jdbcDetails.setUsersByUsernameQuery("SELECT user, pwd, enabled FROM users WHERE user =?");
        jdbcDetails.setAuthoritiesByUsernameQuery("SELECT user, rol FROM roles WHERE user = ?");
        
        return jdbcDetails;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(cus -> cus.disable())
            .authorizeHttpRequests(auth -> 
                auth.requestMatchers(HttpMethod.POST, "/contactos").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/contactos/**").hasAnyRole("ADMIN", "OPERATOR")
                    .requestMatchers("/contactos").authenticated()
                    .anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
