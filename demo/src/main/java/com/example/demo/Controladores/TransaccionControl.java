package com.example.demo.Controladores;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.usuarioLog;
import com.example.demo.Entidades.producto.Producto;
import com.example.demo.Entidades.producto.ProductoRepositorio;
import com.example.demo.Entidades.transaccion.Transaccion;
import com.example.demo.Entidades.transaccion.TransaccionRepositorio;
import com.example.demo.Entidades.usuario.Usuario;

@Controller
@RequestMapping("/Gestion_Inventarios/Transaccion")
public class TransaccionControl {

    @Autowired TransaccionRepositorio repositorio;
    @Autowired ProductoRepositorio repositorioPo;
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
        return "redirect:/Gestion_Inventarios/Tienda/";
    }

    @GetMapping("/Lista")
    public String listaTransaccion(Model model){

        Usuario usuario = usuarioLog.nombreUsuario();

        if(usuario.getTipousuario() == 2){
            model.addAttribute("Transaccionn", repositorio.findByProveedor(usuario.getID_Usuario(), 0));
        }else if (usuario.getTipousuario() == 1) {
            model.addAttribute("Transaccionn", repositorio.findByUsuario(usuario.getID_Usuario(), 1));
        }
        return "Tienda/ListTransaccion";
    }

    
}
