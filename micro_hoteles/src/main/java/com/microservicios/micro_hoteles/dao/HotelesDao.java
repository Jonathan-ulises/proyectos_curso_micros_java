package com.microservicios.micro_hoteles.dao;

import java.util.List;

import com.microservicios.micro_hoteles.models.Hotel;

public interface HotelesDao {

    public List<Hotel> devolverHoteles();
}
