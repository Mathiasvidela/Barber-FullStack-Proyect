package com.barber.barberia_api.controller;

import com.barber.barberia_api.entity.Especialidad;
import com.barber.barberia_api.service.EspecialidadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")

public class EspecialidadController {

    private final   EspecialidadService service;

    public EspecialidadController(EspecialidadService service) {
        this.service = service;
    }

    @GetMapping
    public List<Especialidad> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidad crear(@RequestBody Especialidad especialidad) {
        return service.crear(especialidad);
    }

}
