package com.microservicios.microservicios_cliente_contactos_05.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicios.microservicios_cliente_contactos_05.model.Persona;

@Service
public class AccesoServiceImpl implements AccesoService {

    @Autowired
    private RestTemplate template;
    private final String URL = "http://localhost:8080";
    
    @Override
    @Async
    public CompletableFuture<List<Persona>> llamadaServicio(Persona persona) {
        template.postForLocation(URL + "/contactos", persona);
        Persona[] personas = template.getForObject(URL + "/contactos", Persona[].class);
        
        return CompletableFuture.completedFuture(Arrays.asList(personas));
    }

    
}
