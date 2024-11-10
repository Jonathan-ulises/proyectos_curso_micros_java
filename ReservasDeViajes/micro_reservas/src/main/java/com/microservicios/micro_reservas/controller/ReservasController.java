package com.microservicios.micro_reservas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.micro_reservas.models.Reserva;
import com.microservicios.micro_reservas.services.ReservasService;

@CrossOrigin(origins = "*")
@RestController
public class ReservasController {

    @Autowired
    private ReservasService service;

    @PostMapping(value = "/reservas/{personas}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void generarReserva(@RequestBody Reserva reserva, @PathVariable("personas") int personas) {
        this.service.realizarReserva(reserva, personas);
    }

    @GetMapping(value = "/reservas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reserva> getReservas() {
        return this.service.getReservas();
    }
}
