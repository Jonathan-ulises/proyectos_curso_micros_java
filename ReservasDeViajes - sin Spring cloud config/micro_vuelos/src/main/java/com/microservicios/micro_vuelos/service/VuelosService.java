package com.microservicios.micro_vuelos.service;

import java.util.List;

import com.microservicios.micro_vuelos.models.Vuelo;

public interface VuelosService {

    List<Vuelo> recuperarVuelosDisponibles(int plazas);

    void actualizarPlazas(int id, int plazas);
}
