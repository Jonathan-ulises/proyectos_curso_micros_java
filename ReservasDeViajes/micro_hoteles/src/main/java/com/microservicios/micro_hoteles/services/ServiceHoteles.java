package com.microservicios.micro_hoteles.services;

import java.util.List;

import com.microservicios.micro_hoteles.models.Hotel;

public interface ServiceHoteles {

    public List<Hotel> devolverHotelesDisponibles();
}
