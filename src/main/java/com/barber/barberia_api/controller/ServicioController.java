package com.barber.barberia_api.controller;


import com.barber.barberia_api.entity.Servicio;
import com.barber.barberia_api.service.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")

public class ServicioController {

    public ServicioService service;

    public ServicioController(ServicioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Servicio> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servicio create(@RequestBody Servicio servicio) {
        return service.crear(servicio);
    }

}
