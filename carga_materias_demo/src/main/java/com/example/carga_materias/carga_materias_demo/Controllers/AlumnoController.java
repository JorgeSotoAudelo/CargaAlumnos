package com.example.carga_materias.carga_materias_demo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;






@Controller
@RequestMapping("/alumno")
public class AlumnoController {
    
    @GetMapping("/home")
    public String homeAlumnos(Model m) {
        return "alumnos";
    
    }

}
