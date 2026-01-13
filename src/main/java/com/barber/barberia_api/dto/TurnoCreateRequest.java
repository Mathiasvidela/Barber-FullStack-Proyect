package com.barber.barberia_api.dto;

import java.time.LocalDateTime;

public class TurnoCreateRequest {
    public Integer clienteId;
    public Integer empleadoId;
    public Integer servicioId;
    public LocalDateTime inicio;
    public LocalDateTime fin;

}
