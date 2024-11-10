package com.microservicios.microservicio_contacto_04.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservicios.microservicio_contacto_04.model.Contacto;

@Repository
public class AgendaDaoImpl implements AgendaDao {

    @Autowired
    private AgendaJpaSpring agenda;

    @Override
    public void agregarContacto(Contacto contacto) {
        if (contacto != null)
            agenda.save(contacto);
    }

    @Override
    public Contacto recuperarContacto(String email) {
        return agenda.findByEmail(email);
    }

    @Override
    public void eliminarContacto(String email) {
        agenda.eliminarPorEmail(email);
    }

    @Override
    public List<Contacto> devolverContactos() {
        return agenda.findAll();
    }

    @Override
    public void eliminarContacto(int idContacto) {
        agenda.deleteById(idContacto);
    }

    @Override
    public Contacto recuperarContacto(int idContacto) {
        return agenda.findById(idContacto).orElse(null);
    }

    @Override
    public void actualizarContacto(Contacto contacto) {
        if (contacto != null)
            agenda.save(contacto);
    }

}
