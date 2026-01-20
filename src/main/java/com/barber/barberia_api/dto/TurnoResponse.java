package com.barber.barberia_api.dto;

import java.time.LocalDateTime;

import com.barber.barberia_api.entity.EstadoTurno;
import com.barber.barberia_api.entity.Turno;

public class TurnoResponse {

    public Long id;

    public Integer clienteId;
    public String clienteNombre;

    public Integer empleadoId;
    public String empleadoNombre;

    public Integer servicioId;
    public String servicioNombre;

    public LocalDateTime inicio;
    public LocalDateTime fin;

    public String estado;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public static TurnoResponse from(Turno turno) {
        TurnoResponse resp = new TurnoResponse();

        resp.id = turno.getId();

        resp.clienteId = turno.getCliente().getId();
        resp.clienteNombre = turno.getCliente().getNombre() + " " + turno.getCliente().getApellido();

        resp.empleadoId = turno.getEmpleado().getId();
        resp.empleadoNombre = turno.getEmpleado().getNombre() + " " + turno.getEmpleado().getApellido();

        resp.servicioId = turno.getServicio().getId();
        resp.servicioNombre = turno.getServicio().getNombre();

        resp.inicio = turno.getInicio();
        resp.fin = turno.getFin();
        resp.estado = turno.getEstado().name();


        return resp;
    }

    
}
