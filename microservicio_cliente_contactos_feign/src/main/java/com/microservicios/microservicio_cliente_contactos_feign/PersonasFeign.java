package com.microservicios.microservicio_cliente_contactos_feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservicios.microservicio_cliente_contactos_feign.model.Persona;

@FeignClient(value = "microservicio-contacto")
public interface PersonasFeign {

    @GetMapping(value = "/contactos", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Persona> getPersonas();

    @PostMapping(value = "/contactos", consumes = MediaType.APPLICATION_JSON_VALUE)
    void altaPersonas(@RequestBody Persona persona);
}
