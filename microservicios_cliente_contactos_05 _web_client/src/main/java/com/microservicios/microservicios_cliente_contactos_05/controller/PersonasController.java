package com.microservicios.microservicios_cliente_contactos_05.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservicios.microservicios_cliente_contactos_05.model.Persona;

@RestController
public class PersonasController {

    @Autowired
    private WebClient webClient;
    private final String URL = "http://localhost:8080";

    @Value("${app.user}")
    private String user;

    @Value("${app.password}")
    private String pass;
    

    @GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaPersona(
        @PathVariable("nombre") String nombre,
        @PathVariable("email") String email,
        @PathVariable("edad") int edad) {

        Persona persona = new Persona(nombre, email, edad);
        

        webClient
            .post() // RequestBodyUriSpect
            .uri(URL + "/contactos") // RequestBodySpect
            .contentType(MediaType.APPLICATION_JSON) // RequestBodySpect
            .bodyValue(persona) // RequestHeadersSpec
            .header("Authorization", "Basic " + getBase64(user, pass))
            .retrieve() // ResponseSpec
            .bodyToMono(Void.class) // Mono<Void>
            .block(); // Bloquear hasta la espera de la respuesta // Void

        Persona[] personas = webClient
            .get() // RequestHeadersUriSpect
            .uri(URL + "/contactos") // RequestHeadersSpect
            .header("Authorization", "Basic " + getBase64(user, pass))
            .retrieve() // ResponseSpec
            .bodyToMono(Persona[].class) // Mono<Persona[]>
            .block(); // Bloqueado hasta la espera de la respuesta // Persona[]


        return Arrays.asList(personas);
        
    }

    private String getBase64(String usuario, String password) {
        String cad = usuario + ":" + password;
        return Base64.getEncoder().encodeToString(cad.getBytes());

    }
}
