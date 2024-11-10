package com.microservicios.micro_reservas.services;

import java.util.List;

import com.microservicios.micro_reservas.models.Reserva;

public interface ReservasService {

    void realizarReserva(Reserva reserva, int totalPlazas);

    List<Reserva> getReservas();
}
