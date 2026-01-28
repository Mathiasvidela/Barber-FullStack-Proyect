package com.barber.barberia_api.service;


import com.barber.barberia_api.dto.LoginRequest;
import com.barber.barberia_api.dto.LoginResponse;
import com.barber.barberia_api.entity.Usuario;
import com.barber.barberia_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepo;

    public AuthService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public LoginResponse login(LoginRequest req){

        if(req.username == null || req.username.isBlank())
            throw new IllegalArgumentException("Username requerido");

        if(req.password == null || req.password.isBlank())
            throw new IllegalArgumentException("Password requerido");

        Usuario u = usuarioRepo.findByUsername(req.username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        //validacion de texto basico, luego cambiar
        if(!u.getPassword().equals(req.password))
            throw new IllegalArgumentException("Usuario o contraseña incorrecta");

        return LoginResponse.from(u);

    }

}
