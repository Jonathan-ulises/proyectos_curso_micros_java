package com.microservicios.microservicios_cliente_contactos_05.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.microservicios.microservicios_cliente_contactos_05.model.Persona;

public interface AccesoService {
    CompletableFuture<List<Persona>> llamadaServicio(Persona persona);
}
