package com.microservicios.microservicio_contacto_04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.microservicio_contacto_04.model.Contacto;
import com.microservicios.microservicio_contacto_04.service.AgendaService;

@RestController
public class ContactosController {

    @Autowired
    private AgendaService service;

    @GetMapping(value = "/contactos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contacto>> recuperarContactos() {
        List<Contacto> contactos = service.recuperarContactos();
        HttpHeaders headers = new HttpHeaders();
        headers.add("total", String.valueOf(contactos.size()));
        return new ResponseEntity<List<Contacto>>(contactos, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/contactos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contacto> recuperarContacto(@PathVariable("id") int id) {
        return new ResponseEntity<Contacto>(service.buscarContacto(id), HttpStatus.OK);
    }

    @PostMapping(value = "/contactos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> guardarContacto(@RequestBody Contacto contacto) throws Exception {
        service.agregarContacto(contacto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping(value = "/contactos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> actualizarContacto(@RequestBody Contacto contacto) {
        service.actualizarContacto(contacto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminarPorId/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable("id") int idContacto) {
        service.eliminarContacto(idContacto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
