package com.example.carga_materias.carga_materias_demo.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.carga_materias.carga_materias_demo.Entities.Materia;

public interface MateriaRepository extends CrudRepository<Materia,Integer> {
    
}
