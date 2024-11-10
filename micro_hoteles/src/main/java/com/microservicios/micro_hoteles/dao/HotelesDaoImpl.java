package com.microservicios.micro_hoteles.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservicios.micro_hoteles.models.Hotel;

@Repository
public class HotelesDaoImpl implements HotelesDao{

    @Autowired
    private HotelesJpaSpring hoteles;

    @Override
    public List<Hotel> devolverHoteles() {
        return hoteles.findAll();
    }

}
