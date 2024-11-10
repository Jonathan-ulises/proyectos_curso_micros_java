package com.microservicios.microservicio_contacto_04.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.microservicios.microservicio_contacto_04.model.Contacto;


public interface AgendaJpaSpring extends JpaRepository<Contacto, Integer> {

    Contacto findByEmail(String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM Contacto c WHERE c.email=?1")
    void eliminarPorEmail(String email);
}
