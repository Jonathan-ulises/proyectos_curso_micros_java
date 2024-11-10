package com.microservicios.microservicio_cursos_02.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.microservicio_cursos_02.model.Curso;

import jakarta.annotation.PostConstruct;

@RestController
public class CursoController {

    private List<Curso> cursos;

    @PostConstruct
    public void init() {
        cursos = new ArrayList<>();
        cursos.add(new Curso("Spring", 25, "tarde"));
        cursos.add(new Curso("Spring Boot", 20, "tarde"));
        cursos.add(new Curso("Python", 30, "tarde"));
        cursos.add(new Curso("Java EE", 50, "fin de semana"));
        cursos.add(new Curso("Java básico", 30, "mañana"));

    }

    @GetMapping(value = "/cursos", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Curso> getCursos() {
        return cursos;
    }

    @GetMapping(value = "/curso", produces = MediaType.APPLICATION_XML_VALUE)
    public Curso getCurso() {
        return new Curso("Java", 100, "mañana");
    }

    @GetMapping(value = "/cursos/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Curso> buscarCursos(@PathVariable("name") String nombre) {
        return this.cursos.stream().filter(c -> c.getNombre().toLowerCase().contains(nombre)).collect(Collectors.toList());
    }
}
