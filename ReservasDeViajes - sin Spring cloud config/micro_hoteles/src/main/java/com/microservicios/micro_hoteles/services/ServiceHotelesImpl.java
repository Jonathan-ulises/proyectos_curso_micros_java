package com.microservicios.micro_hoteles.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.micro_hoteles.dao.HotelesDao;
import com.microservicios.micro_hoteles.models.Hotel;

@Service
public class ServiceHotelesImpl implements ServiceHoteles {

    @Autowired
    private HotelesDao dao;

    @Override
    public List<Hotel> devolverHotelesDisponibles() {
        List<Hotel> hoteles = dao.devolverHoteles();
        return hoteles.stream().filter(f -> f.getDisponible() == 1).collect(Collectors.toList());
    }

}
