package com.example.demo.Controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.usuarioLog;
import com.example.demo.Entidades.producto.Producto;
import com.example.demo.Entidades.producto.ProductoRepositorio;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/Gestion_Inventarios/Productos")
public class ProductoControlador {
    
    @Autowired ProductoRepositorio repositorio;
    @Autowired usuarioLog usuarioLog;

    @GetMapping("/")
    public String lsitaProductos(Model model){
        model.addAttribute("Productoss", repositorio.findByUsuario(usuarioLog.nombreUsuario()));
        return "ProductoUsuario/listaproductos";
    }

    @GetMapping("NuevoProducto/")
    public String nuevoProducto(Model model){
        model.addAttribute("Productoss", new Producto());
        return "ProductoUsuario/NuevoProducto";
    }

    @PostMapping("GuardarProducto/")
    public String guardarProducto(Producto producto){
        if(producto.getProveedor() == null){
            producto.setUsuario(usuarioLog.nombreUsuario());
            producto.setProveedor(usuarioLog.nombreUsuario());
        }
        
        repositorio.save(producto);
        return "redirect:/Gestion_Inventarios/Productos/";
    }

    @GetMapping("BorrarProducto/{id}")
    public String borrarProducto(@PathVariable int id){
        repositorio.deleteById(id);
        return "redirect:/Gestion_Inventarios/Productos/";
    }

    @GetMapping("EditarProducto/{id}")
    public String editarProducto(@PathVariable int id, Model model){
        model.addAttribute("Productoss", repositorio.findById(id).get());
        return "ProductoUsuario/EditarProducto";
    }
}
