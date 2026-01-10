package com.barber.barberia_api.service;

import com.barber.barberia_api.entity.Cliente;
import com.barber.barberia_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    //GET /api/clientes
    public List<Cliente> listar() {
        return repo.findAll();
    }

    //POST api/clientes
    public Cliente crear(Cliente cliente) {

        //condicional para nombre y apellido obligario
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new IllegalArgumentException("nombre es obligatorio");
        }
        if (cliente.getApellido() == null || cliente.getApellido().isBlank()) {
            throw new IllegalArgumentException("apellido es obligatorio");
        }

        //id null para que la genere
        cliente.setId(null);
        return repo.save(cliente);

    }

}
