package com.barber.barberia_api.repository;

import com.barber.barberia_api.entity.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
    Optional<Especialidad> findByNombreIgnoreCase(String nombre);
}
