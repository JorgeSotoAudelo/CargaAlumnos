package com.example.carga_materias.carga_materias_demo.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carga_materias.carga_materias_demo.Entities.Materia;
import com.example.carga_materias.carga_materias_demo.Repositories.MateriaRepository;

@Service
public class MateriaService {
    
    @Autowired
   private MateriaRepository materiaRepo;

   public Optional<Materia> getMateriaByID(int id){
        return materiaRepo.findById(id);
    }

    public boolean saveMateria(Materia m){
        if(m != null){
        materiaRepo.save(m);
        return true;
        }
        return false;
    }

    public Iterable<Materia> getAllMaterias(){
        return materiaRepo.
        findAll();
    }


    public boolean deleteMateriaByID(int id){
        if(materiaRepo.existsById(id)){
            materiaRepo.deleteById(id);
            return true;
        }
        return false;
        
    }



    
    
}
