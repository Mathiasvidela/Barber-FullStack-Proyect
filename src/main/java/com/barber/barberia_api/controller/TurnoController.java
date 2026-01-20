package com.barber.barberia_api.controller;

import com.barber.barberia_api.dto.TurnoCreateRequest;
import com.barber.barberia_api.entity.Turno;
import com.barber.barberia_api.service.TurnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final TurnoService service;

    public TurnoController(TurnoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Turno> listar() {
        return service.listar();
    }

    @PostMapping
    public Turno crear(@RequestBody TurnoCreateRequest req) {
        return service.crear(req);
    }

    @PatchMapping("/{id}/confirmar")
    public Turno confirmar(@PathVariable Long id) {
        return service.confirmar(id);
    }

    @PatchMapping("/{id}/cancelar")
    public Turno cancelar(@PathVariable Long id) {
        return service.cancelar(id);
    }

    @PatchMapping("/{id}/completar")
    public Turno completar(@PathVariable Long id) {
        return service.completar(id);
    }



}
