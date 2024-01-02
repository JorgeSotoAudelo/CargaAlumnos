package com.example.carga_materias.carga_materias_demo.Config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.carga_materias.carga_materias_demo.Services.AuthAlumnoService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private AuthAlumnoService authService;



 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
        .requestMatchers("/js/**").permitAll()
        .requestMatchers("/admin/**").hasAuthority("ADMIN")
        .requestMatchers("/alumno/**").hasAnyAuthority("ALUMNO","ADMIN")
        .requestMatchers("/**").permitAll()
        )
        .userDetailsService(authService)
        .formLogin((form) -> form
        .loginPage("/login")
        .permitAll()
        .defaultSuccessUrl("/pos-login",true)
        )
        .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}
