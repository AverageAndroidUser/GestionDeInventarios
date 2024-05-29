package com.example.demo.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.usuarioLog;
import com.example.demo.Entidades.producto.Producto;
import com.example.demo.Entidades.producto.ProductoRepositorio;
import com.example.demo.Entidades.usuario.UsuarioRepositorio;

@Controller
@RequestMapping("/Gestion_Inventarios/Tienda")
public class Tienda {
    
    @Autowired ProductoRepositorio repositorio;
    @Autowired UsuarioRepositorio repositorioUsu;
    @Autowired usuarioLog usuarioLog;

    @GetMapping("/{pagina}")
    public String listaTienda(@PathVariable("pagina") int pagina, @RequestParam(defaultValue = "10")int tamaño, @RequestParam(defaultValue = "cantidad") String orden, @RequestParam(defaultValue = "asc") String direccion, Model model){
        Sort sort = Sort.by(direccion.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, orden);
        Pageable pageable = PageRequest.of(pagina, tamaño, sort);
        model.addAttribute("Productoss", repositorio.findByCantidad(2, 0, pageable));
        model.addAttribute("Proveedoress", repositorioUsu.findAllProveedor());
        model.addAttribute("orden", orden);
        model.addAttribute("direccion", direccion);
        return "Tienda/ListaTienda";
    }

    @GetMapping("/Busqueda")
    @ResponseBody
    public List<Producto> buscarProducto(@RequestParam String nombre, Model model){
        return repositorio.findByNombreAndUsuarioTi(nombre);
        
    }

    @GetMapping("/filtro")
    @ResponseBody
    public List<Producto> fitraProductos(@RequestParam String proveedor, Model model){
        System.out.println(proveedor);
        if (proveedor.isEmpty()) {
            return repositorio.findByCantidad(2, 0);
        }else{
            return repositorio.findByProveedor(proveedor);
        }
    }

}
