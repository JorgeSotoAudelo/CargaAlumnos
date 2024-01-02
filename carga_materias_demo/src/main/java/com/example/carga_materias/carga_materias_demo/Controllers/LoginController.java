package com.example.carga_materias.carga_materias_demo.Controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class LoginController {
        @GetMapping("/")
    public String root() {
        return "redirect:/pos-login";
    }
    

    @GetMapping("/login")
    public String inicio() {
        return "login";
    }


    @GetMapping("/pos-login")
    public String loginRedirect() {
        String rol = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if(rol.equals("[ALUMNO]")){
            return "redirect:/alumno/home";
        }else if(rol.equals("[ADMIN]")){
            return "redirect:/admin/home";
        }else{
            return "redirect:/login";
        }
        
    }

    
}
