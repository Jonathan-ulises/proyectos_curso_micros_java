package com.microservicios.micro_vuelos.dao;

import java.util.List;

import com.microservicios.micro_vuelos.models.Vuelo;

public interface VuelosDao {

    public List<Vuelo> devolverVuelos();
    public Vuelo devolverVuelo(int idVuelo);
    public void actualizarVuelo(Vuelo vuelo);
}
