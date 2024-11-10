package com.microservicios.micro_reservas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservicios.micro_reservas.dao.ReservasDao;
import com.microservicios.micro_reservas.models.Reserva;

@Service
public class ReservasServiceImpl implements ReservasService {

    @Autowired
    private ReservasDao dao;

    @Autowired
    private RestTemplate template;

    private final String URL = "http://servicio-vuelos";

    @Override
    public void realizarReserva(Reserva reserva, int totalPlazas) {
        dao.generarReserva(reserva);
        // Como no le pasamos nada en el cuerpo, ponemos null
        template.put(URL + "/vuelos/{p1}/{p2}", null, reserva.getVuelo(), totalPlazas);
    }

    @Override
    public List<Reserva> getReservas() {
        return dao.getReservas();
    }

}
