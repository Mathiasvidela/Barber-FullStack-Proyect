package com.barber.barberia_api.service;

import com.barber.barberia_api.dto.TurnoCreateRequest;
import com.barber.barberia_api.entity.*;
import com.barber.barberia_api.repository.*;

import jakarta.transaction.Transactional;

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
        if (req.inicio.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("No se puede crear un turno en el pasado");


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

    //cambios de estado

    //-----------Confirmar turno-----------
    @Transactional
    public Turno confirmar (long turnoId){

        Turno turno = turnoRepo.findById(turnoId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el turno: " + turnoId));

                //turno cancelado
                if (turno.getEstado() == EstadoTurno.CANCELADO){
                    throw new IllegalArgumentException("No se puede confirmar un turno cancelado");
                } 

                //turno completado
                if (turno.getEstado() == EstadoTurno.COMPLETADO){
                    throw new IllegalArgumentException("No se puede confirmar un turno completado");
                }

                //turno pasado
                if (turno.getInicio().isBefore(LocalDateTime.now())){
                    throw new IllegalArgumentException("No se puede confirmar un turno pasado");
                }

                turno.setEstado(EstadoTurno.CONFIRMADO);
                turno.setUpdatedAt(LocalDateTime.now());
                return turnoRepo.save(turno);

    }

    //-----------cancelar turno-----------
    @Transactional
    public Turno cancelar (long turnoId){

        Turno turno = turnoRepo.findById(turnoId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el turno: " + turnoId));

                if (turno.getEstado() == EstadoTurno.COMPLETADO){
                    throw new IllegalArgumentException("No se puede cancelar un turno completado");
                }

                turno.setEstado(EstadoTurno.CANCELADO);
                turno.setUpdatedAt(LocalDateTime.now());
                return turnoRepo.save(turno);

    }

    //----------completar turno-----------
    @Transactional
    public Turno completar (long turnoId){

        Turno turno = turnoRepo.findById(turnoId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el turno: " + turnoId));

                if (turno.getEstado() != EstadoTurno.CONFIRMADO){
                    throw new IllegalArgumentException("Solo se pueden completar turnos confirmados");
                }


              turno.setEstado(EstadoTurno.COMPLETADO);
              turno.setUpdatedAt(LocalDateTime.now());
              return turnoRepo.save(turno);

    }

}