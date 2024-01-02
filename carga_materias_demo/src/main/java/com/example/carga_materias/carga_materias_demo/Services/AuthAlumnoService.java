package com.example.carga_materias.carga_materias_demo.Services;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


import com.example.carga_materias.carga_materias_demo.Entities.Alumno;
import com.example.carga_materias.carga_materias_demo.Repositories.AlumnoRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
@Service
public class AuthAlumnoService implements UserDetailsService{

    @Autowired
    private AlumnoRepository alumnoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Alumno> alumno = alumnoRepo.findByCorreo(username);
        
        if(alumno.isEmpty()) throw new UsernameNotFoundException("Usuario no encontrado");

        Alumno aalumno = alumno.get();
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(aalumno.getRol()));
        
        System.out.println("*******CORREO*******"+aalumno.getCorreo());
        System.out.println("*******ROL*******"+aalumno.getRol());
        return User.builder()
        .username(aalumno.getCorreo())
        .password(aalumno.getContrasenia())
        .authorities(authorities)
        .build();
    
}
}