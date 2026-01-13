package com.barber.barberia_api.repository;

import com.barber.barberia_api.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface TurnoRepository extends JpaRepository<Turno, Long> {

    //query para evitar el duplicados de datos

    boolean existsByEmpleadoIdAndInicioLessThanAndFinGreaterThan(
            Integer empleadoId,
            LocalDateTime finNuevo,
            LocalDateTime inicioNuevo
    );



}
