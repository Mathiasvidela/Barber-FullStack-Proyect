package com.barber.barberia_api.controller;

import com.barber.barberia_api.dto.LoginRequest;
import com.barber.barberia_api.dto.LoginResponse;
import com.barber.barberia_api.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }


}
