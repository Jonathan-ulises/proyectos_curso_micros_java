package com.microservicios.micro_reservas.dao;

import java.util.List;

import com.microservicios.micro_reservas.models.Reserva;

public interface ReservasDao {

    public void generarReserva(Reserva reserva);

    List<Reserva> getReservas();
}
