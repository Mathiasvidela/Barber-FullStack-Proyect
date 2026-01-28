package com.barber.barberia_api.dto;

public class LoginResponse {

    public Integer id;
    public String Username;
    public String role;

    public static LoginResponse from(com.barber.barberia_api.entity.Usuario usuario) {

        LoginResponse response = new LoginResponse();
        response.id = usuario.getId();
        response.Username = usuario.getUsername();
        response.role = usuario.getRole();
        return response;

    }



}
