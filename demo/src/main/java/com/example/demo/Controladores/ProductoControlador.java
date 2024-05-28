package com.example.demo.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.usuarioLog;
import com.example.demo.Entidades.producto.Producto;
import com.example.demo.Entidades.producto.ProductoRepositorio;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/Gestion_Inventarios/Productos")
public class ProductoControlador {
    
    @Autowired ProductoRepositorio repositorio;
    @Autowired usuarioLog usuarioLog;

    @GetMapping("/{pagina}")
    public String lsitaProductos(@PathVariable("pagina") int pagina, @RequestParam(defaultValue = "10") int tamaño, Model model){
        Pageable pageable = PageRequest.of(pagina, tamaño);
        Page<Producto> productos = repositorio.findByUsuario(usuarioLog.nombreUsuario(), pageable);
        model.addAttribute("Productoss", productos);
        return "ProductoUsuario/listaproductos";
    }

    @GetMapping("/Busqueda")
    @ResponseBody
    public List<Producto> buscarProducto(@RequestParam String nombre, Model model){
        return repositorio.findByNombreAndUsuario(nombre, usuarioLog.nombreUsuario());
        
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
        return "redirect:/Gestion_Inventarios/Productos/0";
    }

    @GetMapping("BorrarProducto/{id}")
    public String borrarProducto(@PathVariable int id){
        repositorio.deleteById(id);
        return "redirect:/Gestion_Inventarios/Productos/0";
    }

    @GetMapping("EditarProducto/{id}")
    public String editarProducto(@PathVariable int id, Model model){
        model.addAttribute("Productoss", repositorio.findById(id).get());
        return "ProductoUsuario/EditarProducto";
    }

    @GetMapping("/VerProducto/{id}")
    public String verProducto(@PathVariable int id, Model model){
        model.addAttribute("Procuctoss", repositorio.findById(id).get());
        return "ProductoUsuario/VerProducto";
    }
}
