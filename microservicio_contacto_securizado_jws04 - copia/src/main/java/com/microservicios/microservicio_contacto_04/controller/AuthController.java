package com.microservicios.microservicio_contacto_04.controller;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.microservicios.microservicio_contacto_04.utils.Constantes.CLAVE;
import static com.microservicios.microservicio_contacto_04.utils.Constantes.TIEMPO_VIDA;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class AuthController {

    AuthenticationManager authManager;

    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }


    @PostMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> login(@RequestParam("user") String user,@RequestParam("pwd") String pwd) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user, pwd));   
            return new ResponseEntity<>(getToken(authentication), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    private String getToken(Authentication authentication) {

        String token = Jwts.builder()
                            .setIssuedAt(new Date()) // fecha creacion
                            .setSubject(authentication.getName()) // Usuario
                            .claim("authorities", authentication.getAuthorities().stream() // roles
                                                                .map(GrantedAuthority::getAuthority)
                                                                .collect(Collectors.toList()))
                            .setExpiration(new Date(System.currentTimeMillis() + TIEMPO_VIDA)) // Fecha de exipracion
                            .signWith(Keys.hmacShaKeyFor(CLAVE.getBytes())) // Clave y algoritmo de exncriptacion
                            .compact(); // generacion del token

        return token;
    }
}
