package com.example.carga_materias.carga_materias_demo.Services;

import java.util.Optional;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carga_materias.carga_materias_demo.Entities.Alumno;
import com.example.carga_materias.carga_materias_demo.Entities.Materia;
import com.example.carga_materias.carga_materias_demo.Repositories.AlumnoRepository;


@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepo;


    @Autowired
    private MateriaService materiaRepo;

    public Alumno getAlumnoByID(int id){
        return alumnoRepo.findById(id).orElse(null);
    }

    public boolean saveAlummno(Alumno a){
        if(a != null)
        {
            alumnoRepo.save(a);
            return true;
        }
        return false;
    }
    public Alumno getAlumnoByCorreo(String correo){
        return alumnoRepo.findByCorreo(correo).orElse(null);
    }

    public Iterable<Alumno> getAllAlumnos(){
        return alumnoRepo.findAll();
    }

    public boolean deleteAlumnoByID(int id){
        if(alumnoRepo.existsById(id)){
            alumnoRepo.deleteById(id);
            return true;
        }
        return false;
    }
    public Iterable<Materia> getUnLoadedSubjects(int id) {
        Optional<Alumno> optAlumno = alumnoRepo.findById(id);

        if (optAlumno.isPresent()) {
            Alumno alumno = optAlumno.get();

            // Get all Materias from the repository
            Iterable<Materia> allMaterias = materiaRepo.getAllMaterias();

            // Create a list to store unloaded subjects
            List<Materia> unloadedSubjects = new ArrayList<>();

            // Iterate through allMaterias to filter unloaded subjects
            for (Materia materia : allMaterias) {
                boolean found = false;

                // Check if the materia exists in alumno's materias
                for (Materia alumnoMateria : alumno.getMaterias()) {
                    if (alumnoMateria.getId() == materia.getId()) {
                        found = true;
                        break;
                    }
                }

                // If not found, add to unloadedSubjects
                if (!found) {
                    unloadedSubjects.add(materia);
                }
            }

            return unloadedSubjects;
        }

        return null; // Return null if the alumno with the given ID is not found
    }

    
    public void updateAlumno(Alumno a){
        Optional<Alumno> alumno = alumnoRepo.findById(a.getId());
        if(alumno.isPresent()){

            Alumno alumnoActualizado = alumno.get();
            
            alumnoActualizado.setNombre(a.getNombre());
            alumnoActualizado.setMaterias(a.getMaterias());

            alumnoRepo.save(alumnoActualizado);
        }


    }

    
    public boolean loadSubjectByID(int id, int subjectID){

        Optional<Materia> optMateria = materiaRepo.getMateriaByID(subjectID);

        if(optMateria.isEmpty()) return false;
 
        Optional<Alumno> optAlumno = alumnoRepo.findById(id);

        if(optAlumno.isPresent()){
            Alumno alumno = optAlumno.get();
            Materia materia = optMateria.get();

            alumno.getMaterias().add(materia);

            alumnoRepo.save(alumno);

            return true;
        }

        return  false;
    }

    public boolean removeSubjectFromAlumno(int alumnoId, int materiaId) {
        Optional<Alumno> optAlumno = alumnoRepo.findById(alumnoId);

        if (optAlumno.isPresent()) {
            Alumno alumno = optAlumno.get();

            Materia subjectToRemove = null;
            for (Materia materia : alumno.getMaterias()) {
                if (materia.getId() == materiaId) {
                    subjectToRemove = materia;
                    break;
                }
            }

            if (subjectToRemove != null) {
                alumno.getMaterias().remove(subjectToRemove);
                alumnoRepo.save(alumno);
                return true;
            }
        }
        return false;
    }
}
