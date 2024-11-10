package com.microservicios.microservicio_contacto_04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.microservicio_contacto_04.dao.AgendaDao;
import com.microservicios.microservicio_contacto_04.model.Contacto;

@Service
public class AgendaServiceImpl implements AgendaService{

    @Autowired
    private AgendaDao dao;

    @Override
    public void agregarContacto(Contacto contacto) throws Exception {
        if (dao.recuperarContacto(contacto.getEmail())==null) {
            dao.agregarContacto(contacto);
            return;
        }

        throw new Exception("Contacto repetido");
    }

    @Override
    public List<Contacto> recuperarContactos() {
        return dao.devolverContactos();
    }

    @Override
    public void actualizarContacto(Contacto contacto) {
        if (dao.recuperarContacto(contacto.getIdContacto()) != null)  {
            dao.actualizarContacto(contacto);
        }
    }

    @Override
    public boolean eliminarContacto(int idContacto) {
        if (dao.recuperarContacto(idContacto) != null) {
            dao.eliminarContacto(idContacto);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Contacto buscarContacto(int idContacto) {
        return dao.recuperarContacto(idContacto);
    }

}
