package com.microservicios.micro_vuelos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.micro_vuelos.dao.VuelosDao;
import com.microservicios.micro_vuelos.models.Vuelo;

@Service
public class VuelosServiceImpl implements VuelosService {

    @Autowired
    private VuelosDao dao;

    @Override
    public List<Vuelo> recuperarVuelosDisponibles(int plazas) {
        return dao.devolverVuelos()
                    .stream()
                    .filter(v -> v.getPlazas() >= plazas)
                    .collect(Collectors.toList());
    }

    @Override
    public void actualizarPlazas(int id, int plazas) {
        Vuelo vuelo = dao.devolverVuelo(id);
        if (vuelo != null) {
            vuelo.setPlazas(vuelo.getPlazas() - plazas);
            dao.actualizarVuelo(vuelo);
        }
    }


}
