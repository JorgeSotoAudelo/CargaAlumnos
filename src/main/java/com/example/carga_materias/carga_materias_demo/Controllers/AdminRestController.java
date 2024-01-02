package com.example.carga_materias.carga_materias_demo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carga_materias.carga_materias_demo.Entities.Alumno;
import com.example.carga_materias.carga_materias_demo.Entities.Materia;
import com.example.carga_materias.carga_materias_demo.Services.AlumnoService;
import com.example.carga_materias.carga_materias_demo.Services.MateriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/admin")
public class AdminRestController {
    
    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/GetAlumnos")
    public Iterable<Alumno> getAlumnos() {
        return alumnoService.getAllAlumnos();
    }

    @GetMapping("/GetMaterias")
    public Iterable<Materia> getMaterias(){
        return materiaService.getAllMaterias();
    }

    @PostMapping("/AgregarAlumno")
    public ResponseEntity<?> agregarAlumno(@RequestBody Alumno alumno) {
        if(alumnoService.saveAlummno(alumno)){
            return ResponseEntity.ok().body("Alumno insertado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se pudo agregar el Alumno");
    }

    @DeleteMapping("/EliminarAlumno/{id}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable int id){
        if(alumnoService.deleteAlumnoByID(id)){
            return ResponseEntity.ok().body("Alumno eliminado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no existe");
    }

    @PostMapping("/AgregarMateria")
    public ResponseEntity<?> agregarMateria(@RequestBody Materia m) {
            if(materiaService.saveMateria(m)){
            return ResponseEntity.ok().body("Alumno eliminado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no existe");    
    }

    @DeleteMapping("/EliminarMateria/{id}")
    public ResponseEntity<?> eliminarMateria(@PathVariable int id){
        if(materiaService.deleteMateriaByID(id)){
            return ResponseEntity.ok().body("Alumno eliminado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no existe");
       
    }
    
    
    
}
