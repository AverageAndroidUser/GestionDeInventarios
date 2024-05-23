package com.example.demo.Controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.usuarioLog;
import com.example.demo.Entidades.usuario.UsuarioRepositorio;

@Controller
@RequestMapping("/Gestion_Inventarios/Usuario")
public class UsuarioControlador {
    
    @Autowired usuarioLog usuario;
    @Autowired UsuarioRepositorio repositorio;

    @GetMapping("/")
    public String usuarioInfo(Model model){  
        model.addAttribute("UsuarioInfoo", usuario.nombreUsuario());
        return "Usuario/verUsuario";
    }

    @GetMapping("Proveedor/{id}")
    public String infoProveedor(@PathVariable int id, Model model){
        model.addAttribute("UsuarioInfoo", repositorio.findById(id).get());
        return "Usuario/verUsuario";
    }
}
