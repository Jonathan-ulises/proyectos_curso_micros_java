package com.microservicios.micro_vuelos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.micro_vuelos.models.Vuelo;
import com.microservicios.micro_vuelos.service.VuelosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@CrossOrigin(origins = "*")
@RestController
public class VuelosController {


    @Autowired
    private VuelosService service;

    @GetMapping(value = "/vuelos/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vuelo> devolverVuelos(@PathVariable("plazas") int plazas) {
        return service.recuperarVuelosDisponibles(plazas);
    }

    @PutMapping(value = "/vuelos/{idVuelo}/{plazas}")
    public void putMethodName(@PathVariable("idVuelo") int id, @PathVariable("plazas") int plazas) {
        service.actualizarPlazas(id, plazas);
    }
    
}
