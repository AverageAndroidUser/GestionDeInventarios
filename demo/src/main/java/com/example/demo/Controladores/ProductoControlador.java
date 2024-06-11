package com.example.demo.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.example.demo.Entidades.usuario.UsuarioRepositorio;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/Gestion_Inventarios/Productos")
public class ProductoControlador {
    
    @Autowired ProductoRepositorio repositorio;
    @Autowired UsuarioRepositorio repositorioUs;
    @Autowired usuarioLog usuarioLog;
    
    @GetMapping("/{pagina}")
    public String lsitaProductos(@PathVariable("pagina") int pagina, @RequestParam(defaultValue = "10") int tamaño, @RequestParam(defaultValue = "cantidad") String orden, @RequestParam(defaultValue = "asc") String direccion, Model model){
        Sort sort = Sort.by(direccion.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, orden);
        Pageable pageable = PageRequest.of(pagina, tamaño, sort);
        Page<Producto> productos = repositorio.findByUsuario(usuarioLog.nombreUsuario(), pageable);
        /*List<Usuario> usuarios = repositorioUs.findByUsuarioProveedor(usuarioLog.nombreUsuario());
        usuarios.forEach(pro ->{
            System.out.println(pro.getNom_usuario());
        });*/
        model.addAttribute("Proveedoress", repositorioUs.findByUsuarioProveedor(usuarioLog.nombreUsuario()));
        model.addAttribute("Productoss", productos);
        model.addAttribute("orden", orden);
        model.addAttribute("direccion", direccion);
        return "ProductoUsuario/listaproductos";
    }

    @GetMapping("/Busqueda")
    @ResponseBody
    public List<Producto> buscarProducto(@RequestParam String nombre, Model model){
        return repositorio.findByNombreAndUsuario(nombre, usuarioLog.nombreUsuario());
        
    }

    @GetMapping("/filtro")
    @ResponseBody
    public List<Producto> fitraProductos(@RequestParam String proveedor, Model model){
        System.out.println(proveedor);
        if (proveedor.isEmpty()) {
            return repositorio.findByUsuario2(usuarioLog.nombreUsuario().getID_Usuario());
        }else{
            return repositorio.findByProveedorAndUsuario(proveedor, usuarioLog.nombreUsuario());
        }
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
        Producto producto = repositorio.findById(id).get();
        if(producto.getUsuario() == usuarioLog.nombreUsuario()){
            repositorio.deleteById(id);
            return "redirect:/Gestion_Inventarios/Productos/0";
        }else{
            return "error";
        }
    }

    @GetMapping("EditarProducto/{id}")
    public String editarProducto(@PathVariable int id, Model model){
        Producto producto = repositorio.findById(id).get();
        if(producto.getUsuario() == usuarioLog.nombreUsuario()){
            model.addAttribute("Productoss", producto);
            return "ProductoUsuario/EditarProducto";
        }else{
            return "error";
        }
    }

    @GetMapping("/VerProducto/{id}")
    public String verProducto(@PathVariable int id, Model model){
        Producto producto = repositorio.findById(id).get();
        if(producto.getUsuario().getTipousuario() == 2){
            model.addAttribute("Procuctoss", producto);
            return "ProductoUsuario/VerProducto";
        }else{
            return "error";
        }
        
    }
    
    @GetMapping("/MiProducto/{id}")
    public String miProducto(@PathVariable int id, Model model){
        Producto producto = repositorio.findById(id).get();
        if(producto.getUsuario() == usuarioLog.nombreUsuario()){
            model.addAttribute("Productoo", producto);
            return "ProductoUsuario/MiProducto";
        }else{
            return "error";
        }
    }
}
