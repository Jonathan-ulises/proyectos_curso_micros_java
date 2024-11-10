package com.microservicios.microservicio_contacto_04.service;

import java.util.List;

import com.microservicios.microservicio_contacto_04.model.Contacto;

public interface AgendaService {

    void agregarContacto(Contacto contacto) throws Exception;
    List<Contacto> recuperarContactos();
    void actualizarContacto(Contacto contacto);
    boolean eliminarContacto(int idContacto);
    Contacto buscarContacto(int idContacto);
}
