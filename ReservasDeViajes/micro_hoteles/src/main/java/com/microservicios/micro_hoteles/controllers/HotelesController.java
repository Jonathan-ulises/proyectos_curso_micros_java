package com.microservicios.micro_hoteles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.micro_hoteles.models.Hotel;
import com.microservicios.micro_hoteles.services.ServiceHoteles;
import org.springframework.web.bind.annotation.GetMapping;


// @CrossOrigin(origins = "*")
@RestController
public class HotelesController {

    @Autowired
    private ServiceHoteles service;

    @GetMapping(value = "/hoteles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> devolverHoteles() {
        return service.devolverHotelesDisponibles();
    }
    

}
