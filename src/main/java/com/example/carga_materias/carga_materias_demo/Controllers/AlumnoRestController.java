package com.example.carga_materias.carga_materias_demo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carga_materias.carga_materias_demo.Entities.Alumno;
import com.example.carga_materias.carga_materias_demo.Entities.Materia;
import com.example.carga_materias.carga_materias_demo.Services.AlumnoService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;






@RestController
@RequestMapping("/alumno")
public class AlumnoRestController {
    
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/Get")
    public Alumno getAlumno() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Alumno alumno = alumnoService.getAlumnoByCorreo(correo);
        return alumno;
    }

    @GetMapping("/GetMaterias")
    public Iterable<Materia> getMaterias() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Alumno alumno = alumnoService.getAlumnoByCorreo(correo);
        return alumno.getMaterias();
    }

    @GetMapping("/GetMateriasNoCargadas")
    public Iterable<Materia> getMateriasNoCargadas(){
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Alumno alumno = alumnoService.getAlumnoByCorreo(correo);

        return alumnoService.getUnLoadedSubjects(alumno.getId());
    }
    
    

    @PatchMapping("/AgregarMateria/{SubjectId}")
    public ResponseEntity<?> cargarMateria(@PathVariable int SubjectId){
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Alumno alumno = alumnoService.getAlumnoByCorreo(correo);

        Boolean added = alumnoService.loadSubjectByID(alumno.getId(),SubjectId);

        
        
        return added?
         ResponseEntity.ok("Materia cargada exitosamente") :
         ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se realizo la carga");
    }

    @PatchMapping("/QuitarMateria/{SubjectId}")
    public ResponseEntity<?> removerMateria(@PathVariable int SubjectId){
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Alumno alumno = alumnoService.getAlumnoByCorreo(correo);

        Boolean removed = alumnoService.removeSubjectFromAlumno(alumno.getId(), SubjectId);

        return removed?
         ResponseEntity.ok("Materia cargada exitosamente") :
         ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se realizo la carga");
    }
    
    
}
