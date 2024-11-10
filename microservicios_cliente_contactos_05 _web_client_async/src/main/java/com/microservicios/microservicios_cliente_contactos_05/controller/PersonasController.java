package com.microservicios.microservicios_cliente_contactos_05.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.microservicios.microservicios_cliente_contactos_05.model.Persona;
import com.microservicios.microservicios_cliente_contactos_05.service.AccesoService;

@RestController
public class PersonasController {


    @Autowired
    private AccesoService service;

    @GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaPersona(
        @PathVariable("nombre") String nombre,
        @PathVariable("email") String email,
        @PathVariable("edad") int edad) {

        Persona persona = new Persona(nombre, email, edad);
 
        CompletableFuture<List<Persona>> resultado = service.llamadaServicio(persona);

        try {
            return resultado.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
