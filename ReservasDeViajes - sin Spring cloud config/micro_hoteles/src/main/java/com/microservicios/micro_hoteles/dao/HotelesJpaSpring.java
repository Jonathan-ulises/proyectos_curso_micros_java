package com.microservicios.micro_hoteles.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicios.micro_hoteles.models.Hotel;

public interface HotelesJpaSpring extends JpaRepository<Hotel, Integer> {

}
