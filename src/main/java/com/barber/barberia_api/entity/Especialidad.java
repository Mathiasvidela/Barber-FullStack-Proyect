package com.barber.barberia_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "especialidades", schema = "public")

public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombre;

    //Getters
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }

    //Setters
    public void setId(Integer id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }

}
