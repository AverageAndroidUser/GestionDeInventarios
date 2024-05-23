package com.example.demo.Controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entidades.departamento.DepartamentoRepositorio;
import com.example.demo.Entidades.usuario.Usuario;
import com.example.demo.Entidades.usuario.UsuarioRepositorio;

@Controller
@RequestMapping("/Gestion_Inventarios")
public class EntradaControlador {

    @Autowired UsuarioRepositorio repositorio;
    @Autowired DepartamentoRepositorio repositorioDe;

    @GetMapping("/register")
    public String registrar(Model modelo){
        modelo.addAttribute("Departamentoss", repositorioDe.findAll());
        modelo.addAttribute("Usuarioss", new Usuario());
        return "registrarForm";
    }
    
    @PostMapping("/guardarUsuario")
    public String guardar(Usuario usuario){
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
        String cont = pass.encode(usuario.getContraseña());
        usuario.setContraseña(cont);
        repositorio.save(usuario);
        return "correcto";
    }
}
