package com.microservicios.micro_reservas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservicios.micro_reservas.models.Reserva;


@Repository
public class ReservasDaoImpl implements ReservasDao {

    @Autowired
    private ReservasJpaSpring reservas;

    @Override
    public void generarReserva(Reserva reserva) {
        reservas.save(reserva);
    }

    @Override
    public List<Reserva> getReservas() {
        return reservas.findAll();
    }

}
