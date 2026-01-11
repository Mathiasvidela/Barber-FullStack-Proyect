package com.barber.barberia_api.service;

import com.barber.barberia_api.entity.Servicio;
import com.barber.barberia_api.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service

public class ServicioService {

    private final ServicioRepository repo;

    public ServicioService(ServicioRepository repo) {
        this.repo = repo;
    }

    //GET
    public List<Servicio> listar() {
        return repo.findAll();
    }

    //POST
    public Servicio crear(Servicio servicio) {

        //Condicionales para que no genere errores con campos vacios o negativos
        if (servicio.getNombre() == null || servicio.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del servicio no puede ser nulo");
        }

        BigDecimal precio = servicio.getPrecio();
        if (precio == null) {
            throw new IllegalArgumentException("El precio del servicio no puede ser nulo");
        }
        if (precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        //id null
        servicio.setId(null);

        return repo.save(servicio);


    }





}
