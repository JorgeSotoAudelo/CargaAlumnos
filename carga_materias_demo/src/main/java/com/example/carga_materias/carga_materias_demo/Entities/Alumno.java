package com.example.carga_materias.carga_materias_demo.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="Alumnos")
public class Alumno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    
    @Column(unique = true)
    private String correo;

    private String contrasenia;
    
    private String userRol;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AsignacionMaterias",
        joinColumns = @JoinColumn(name = "alumno_id"),
        inverseJoinColumns = @JoinColumn(name = "materia_id")
    )

    private Set<Materia> materias = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.userRol = "ALUMNO"; // Ensure userRole is always set to ALUMNO before persisting
    }

    public Alumno () {}

    public Alumno(int id, String nombre, String correo, String contrasenia, Set<Materia> materias) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.materias = materias;
        this.userRol = "ALUMNO";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Set<Materia> getMaterias() {
        return materias;
    }

    public String getRol(){
        return this.userRol;
    }

    public void setMaterias(Set<Materia> materias) {
        this.materias = materias;
    }

}
