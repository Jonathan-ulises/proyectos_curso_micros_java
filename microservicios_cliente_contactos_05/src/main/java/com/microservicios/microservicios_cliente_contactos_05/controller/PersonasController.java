package com.microservicios.microservicios_cliente_contactos_05.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.microservicios.microservicios_cliente_contactos_05.model.Persona;

import jakarta.annotation.PostConstruct;

@RestController
public class PersonasController {

    @Autowired
    private RestTemplate template;
    private final String URL = "http://microservicio-contacto";
    
    private final String USER = "admin";
    private final String PWD = "admin";
    private String token;

    HttpHeaders headers = new HttpHeaders();


    @PostConstruct()
    public void autenticar() {
        token = template.postForObject(URL + "/login?user=" + USER + "&pwd=" + PWD, null, String.class);
        headers.add("Authorization", "Bearer " + token);
    }

    @GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaPersona(
        @PathVariable("nombre") String nombre,
        @PathVariable("email") String email,
        @PathVariable("edad") int edad) {

        Persona persona = new Persona(nombre, email, edad);

        try {
            template.exchange(URL + "/contactos", HttpMethod.POST, new HttpEntity<Persona>(persona, headers), String.class);
            ResponseEntity<Persona[]> personas = template.exchange(URL + "/contactos", HttpMethod.GET, new HttpEntity<>(headers), Persona[].class);    
            return Arrays.asList(personas.getBody());
        } catch (HttpClientErrorException e) {
            // HttpHeaders headers = new HttpHeaders();
            // headers.add("error", e.getResponseBodyAsString());
            // return new ResponseEntity<List<Persona>>(new ArrayList<Persona>(), headers, e.getStatusCode());
            return new ArrayList<>();
        }
    }

    @GetMapping(value = "/personas/{edad1}/{edad2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2) {
        ResponseEntity<Persona[]> personas = template.exchange(URL + "/contactos", HttpMethod.GET, new HttpEntity<>(headers), Persona[].class);    
        return Arrays.stream(personas.getBody())
            .filter(p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
            .collect(Collectors.toList());
    }
}
