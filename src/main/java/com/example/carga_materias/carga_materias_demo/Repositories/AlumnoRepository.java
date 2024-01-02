package com.example.carga_materias.carga_materias_demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.carga_materias.carga_materias_demo.Entities.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno,Integer> {
    Optional<Alumno> findByCorreo(String correo);
}
