package com.example.demo.Controladores;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.usuarioLog;
import com.example.demo.Entidades.producto.Producto;
import com.example.demo.Entidades.producto.ProductoRepositorio;
import com.example.demo.Entidades.transaccion.Transaccion;
import com.example.demo.Entidades.transaccion.TransaccionRepositorio;
import com.example.demo.Entidades.usuario.Usuario;
import com.example.demo.Entidades.usuario.UsuarioRepositorio;

@Controller
@RequestMapping("/Gestion_Inventarios/Transaccion")
public class TransaccionControl {

    @Autowired TransaccionRepositorio repositorio;
    @Autowired ProductoRepositorio repositorioPo;
    @Autowired UsuarioRepositorio repositorioUs;
    @Autowired usuarioLog usuarioLog;

    @GetMapping("/{id}")
    public String tranProducto(@PathVariable int id, Model model){
        Transaccion transaccion = new Transaccion(usuarioLog.nombreUsuario(), repositorioPo.findById(id).get());
        model.addAttribute("Transaccionn", transaccion);
        return "Tienda/Transaccion";
    }

    @PostMapping("/Nueva_Transaccion")
    public String guardarTransaccion(Transaccion transaccion){
        //ENTRADA
        transaccion.setFecha_pedido(new Date());
        transaccion.setTipotransaccion(1);
        repositorio.save(transaccion);

        //SALIDA
        Transaccion transaccionProv = new Transaccion(transaccion.getUsuario(), transaccion.getProducto(), transaccion.getCantidad_total(),
            transaccion.getPrecio_total(), transaccion.getFecha_entrega(), transaccion.getFecha_pedido(), 0);

        repositorio.save(transaccionProv);
        
        Producto compra = transaccion.getProducto();
        Producto nuebProducto = new Producto(transaccion.getProducto().getNombre(), transaccion.getProducto().getDescripcion(), 
            transaccion.getCantidad_total(), transaccion.getProducto().getPreciounitario(), usuarioLog.nombreUsuario(), 
            transaccion.getProducto().getProveedor());
        
        compra.setCantidad(compra.getCantidad() - nuebProducto.getCantidad());
        repositorioPo.save(compra);
        repositorioPo.save(nuebProducto);
        return "redirect:/Gestion_Inventarios/Tienda/0";
    }

    @GetMapping("/Lista/{pagina}")
    public String listaTransaccion(@PathVariable("pagina") int pagina, @RequestParam(defaultValue = "10") int tamaño, Model model){
        //Sort sort = Sort.by(direccion.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, orden);
        Pageable pageable = PageRequest.of(pagina, tamaño);

        Usuario usuario = usuarioLog.nombreUsuario();
        Page<Transaccion> transaccion;

        if(usuario.getTipousuario() == 2){
            transaccion = repositorio.findByProveedor(usuario, pageable);
            model.addAttribute("Transaccionn", transaccion);
            model.addAttribute("Proveedoress", repositorioUs.findAllProveedorTra(usuarioLog.nombreUsuario()));
            model.addAttribute("Usuarioss", repositorioUs.findAllUsuarioTra(usuarioLog.nombreUsuario()));
        }else if (usuario.getTipousuario() == 1) {
            transaccion = repositorio.findByUsuario(usuario, 1, pageable);
            model.addAttribute("Transaccionn", transaccion);
            model.addAttribute("Proveedoress", repositorioUs.findAllProveedorTra(usuarioLog.nombreUsuario()));
        }
        model.addAttribute("Usuarioo", usuario);
        //model.addAttribute("orden", orden);
        //model.addAttribute("direccion", direccion);
        return "Tienda/ListTransaccion";
    }
    
}
