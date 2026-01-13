package com.barber.barberia_api.service;

import com.barber.barberia_api.entity.Empleado;
import com.barber.barberia_api.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    //POST
    public Empleado guardar(Empleado empleado) {
        return repository.save(empleado);
    }

    //GET
    public List<Empleado> listar() {
        return repository.findAll();
    }




}
