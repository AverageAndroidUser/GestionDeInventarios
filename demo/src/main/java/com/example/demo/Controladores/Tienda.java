package com.example.demo.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entidades.producto.ProductoRepositorio;
import com.example.demo.Entidades.usuario.UsuarioRepositorio;

@Controller
@RequestMapping("/Gestion_Inventarios/Tienda")
public class Tienda {
    
    @Autowired ProductoRepositorio repositorio;
    @Autowired UsuarioRepositorio repositorioUsu;

    @GetMapping("/")
    public String listaTienda(Model model){
        model.addAttribute("Productoss", repositorio.findByCantidad(2, 0));
        return "Tienda/ListaTienda";
    }

}
