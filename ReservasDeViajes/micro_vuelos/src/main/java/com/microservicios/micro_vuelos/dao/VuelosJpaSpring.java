package com.microservicios.micro_vuelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.micro_vuelos.models.Vuelo;

public interface VuelosJpaSpring extends JpaRepository<Vuelo, Integer>{

}
