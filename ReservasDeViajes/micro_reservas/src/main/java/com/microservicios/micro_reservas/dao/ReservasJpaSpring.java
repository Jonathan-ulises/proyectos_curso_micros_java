package com.microservicios.micro_reservas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.micro_reservas.models.Reserva;

public interface ReservasJpaSpring extends JpaRepository<Reserva, Integer> {

}
