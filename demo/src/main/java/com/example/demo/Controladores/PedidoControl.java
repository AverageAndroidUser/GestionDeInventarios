package com.example.demo.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entidades.pedido.PedidoRepositorio;

@Controller
@RequestMapping("/Gestion_Inventarios/Pedido")
public class PedidoControl {
    
    @Autowired PedidoRepositorio repositorio;
}
