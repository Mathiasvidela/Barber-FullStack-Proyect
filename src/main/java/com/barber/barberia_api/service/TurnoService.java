package com.barber.barberia_api.service;

import com.barber.barberia_api.dto.TurnoCreateRequest;
import com.barber.barberia_api.entity.*;
import com.barber.barberia_api.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepo;
    private final ClienteRepository clienteRepo;
    private final EmpleadoRepository empleadoRepo;
    private final ServicioRepository servicioRepo;

    public TurnoService (TurnoRepository turnoRepo,
                         ClienteRepository clienteRepo,
                         EmpleadoRepository empleadoRepo,
                         ServicioRepository servicioRepo){

        this.turnoRepo = turnoRepo;
        this.clienteRepo = clienteRepo;
        this.empleadoRepo = empleadoRepo;
        this.servicioRepo = servicioRepo;

    }

    public List<Turno> listar(){
        return turnoRepo.findAll();
    }

    public Turno crear(TurnoCreateRequest req){
        //validaciones

        if (req.inicio == null) throw  new IllegalArgumentException("Se requiere un inicio valido");
        if (req.fin == null) throw  new IllegalArgumentException("Se requiere un fin valido");
        if (!req.fin.isAfter(req.inicio)) throw  new IllegalArgumentException("El fin debe de ser mayor que el inicio");

        //buscamos las entidades FK
        Cliente cliente  = clienteRepo.findById(req.clienteId)
                .orElseThrow(()-> new IllegalArgumentException("No existe el cliente: " + req.clienteId));

        Empleado empleado = empleadoRepo.findById(req.empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el empleado" + req.empleadoId));

        Servicio servicio = servicioRepo.findById(req.servicioId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el servicio: " + req.servicioId));

        //validacion para no superponer turnos

        boolean choca = turnoRepo.existsByEmpleadoIdAndInicioLessThanAndFinGreaterThan(
                req.empleadoId,
                req.fin,
                req.inicio
        );

        if (choca){
            throw new IllegalArgumentException("El empleado ya tiene un turno en ese horario");
        }

        // crear y guardar
        Turno t = new Turno();
        t.setCliente(cliente);
        t.setEmpleado(empleado);
        t.setServicio(servicio);
        t.setInicio(req.inicio);
        t.setFin(req.fin);
        t.setEstado(EstadoTurno.PENDIENTE);

        return turnoRepo.save(t);

    }

}
