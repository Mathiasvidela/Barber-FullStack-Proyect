package com.barber.barberia_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes", schema = "public")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //campos de la tabla
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String telefono;

    private String email;

    //constructor vacio
    public Cliente() {}

    //Setters

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    //Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
