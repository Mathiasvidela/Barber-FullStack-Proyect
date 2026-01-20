package com.barber.barberia_api.controller;

import com.barber.barberia_api.dto.TurnoCreateRequest;
import com.barber.barberia_api.dto.TurnoResponse;
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
    public List<TurnoResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public TurnoResponse crear(@RequestBody TurnoCreateRequest req) {
        return service.crear(req);
    }

    @PatchMapping("/{id}/confirmar")
    public TurnoResponse confirmar(@PathVariable Long id) {
        return service.confirmar(id);
    }



    @PatchMapping("/{id}/cancelar")
    public TurnoResponse cancelar(@PathVariable Long id) { //ver si devuelve el DTO o la entidad
        return service.cancelar(id);
    }

    @PatchMapping("/{id}/completar")
    public TurnoResponse completar(@PathVariable Long id) { //ver si devuelve el DTO o la entidad
        return service.completar(id);
    }



}
