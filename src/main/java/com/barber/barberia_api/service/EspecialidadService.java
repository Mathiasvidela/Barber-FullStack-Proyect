package com.barber.barberia_api.service;

import com.barber.barberia_api.entity.Especialidad;
import com.barber.barberia_api.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService {

    private final EspecialidadRepository repo;

    public EspecialidadService(EspecialidadRepository repo) {
        this.repo = repo;
    }

    //GET
    public List<Especialidad> listar() {
        return repo.findAll();
    }

    //POST
    public Especialidad crear(Especialidad especialidad) {

        if(especialidad.getNombre() == null || especialidad.getNombre().isBlank()) {
            throw new IllegalArgumentException("Nombre del especialidad obligatorio");
        }

        String nombre = especialidad.getNombre().trim();

        //evitar duplicados
        repo.findByNombreIgnoreCase(nombre).ifPresent(especialidad1 -> {
            throw new IllegalArgumentException("El nombre del especialidad ya existe");
        });

        especialidad.setId(null);
        especialidad.setNombre(nombre);
        return repo.save(especialidad);

    }

}
