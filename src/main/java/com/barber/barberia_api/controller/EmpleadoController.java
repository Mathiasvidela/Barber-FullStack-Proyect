package com.barber.barberia_api.controller;

import com.barber.barberia_api.entity.Empleado;
import com.barber.barberia_api.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin

public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {
        return service.guardar(empleado);
    }

    @GetMapping
    public List<Empleado> listar() {
        return service.listar();
    }





}
