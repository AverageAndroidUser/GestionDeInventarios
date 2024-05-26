package com.example.demo.Controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.usuarioLog;
import com.example.demo.Entidades.pedido.Pedido;
import com.example.demo.Entidades.pedido.PedidoRepositorio;
import com.example.demo.Entidades.pedido.detalle_pedido.Detalle_pedido;
import com.example.demo.Entidades.pedido.detalle_pedido.Detalle_pedidoRepositorio;
import com.example.demo.Entidades.producto.Producto;
import com.example.demo.Entidades.producto.ProductoRepositorio;

@Controller
@RequestMapping("/Gestion_Inventarios/Pedido")
public class PedidoControl {
    
    @Autowired PedidoRepositorio repositorio;
    @Autowired Detalle_pedidoRepositorio repositorioDe;
    @Autowired ProductoRepositorio repositorioPo;
    @Autowired usuarioLog usuarioLog;

    List<Detalle_pedido> lista = new ArrayList<>();

    @GetMapping("/ListaPedido")
    public String listaPedidos(Model model){
        model.addAttribute("Detalless", repositorioDe.findByUsuario(usuarioLog.nombreUsuario()));
        return "Pedido/ListaPedido";
    }    

    @GetMapping("/")
    public String nuevoPedido(Model model) {
        lista.clear();
        model.addAttribute("Pedidoo", new Pedido());
        model.addAttribute("Productoss", repositorioPo.findByUsuario2(usuarioLog.nombreUsuario().getID_Usuario()));
        return "Pedido/NuevoPedido";
    }

    @GetMapping("/DetallePedido/{id_pedido}/{cantidad}")
    @ResponseBody /*La anotación @ResponseBody le indica a Spring que el método no debe intentar resolver una vista,
                     sino que debe escribir directamente la respuesta en el cuerpo de la respuesta HTTP.*/
    public void nuevoDetalle(@PathVariable int id_pedido, @PathVariable int cantidad) {
        Detalle_pedido detalle = new Detalle_pedido();
        detalle.setProducto(repositorioPo.findById(id_pedido).get());
        detalle.setCantidad_total(cantidad);
        lista.add(detalle);
    }

    @GetMapping("DetallePedido/Eliminar/{id}")
    @ResponseBody
    public void eliminarDetalle(@PathVariable int id){
        for(int i = 0; i < lista.size(); i++){
            Detalle_pedido detalle_pedido = lista.get(i);
            if(detalle_pedido.getProducto().getID_Producto() == id){
                lista.remove(i);
                break;
            }
        }  
    }

    @PostMapping("/Guardar")
    public String guardarPedido(Pedido pedido){
        pedido.setUsuario(usuarioLog.nombreUsuario());
        pedido.setFecha_salida(new Date());
        if(lista.size() > 0){
            pedido.setEstado(1);
        }else{
            pedido.setEstado(0);
        }

        Pedido pedido2 = repositorio.save(pedido);
        Producto producto = new Producto();

        if(pedido2.getID_pedido() > 0){
            for(int i = 0; i < lista.size(); i++){
                lista.get(i).setPedido(pedido2);
                repositorioDe.save(lista.get(i));
                
                producto = repositorioPo.findById(lista.get(i).getProducto().getID_Producto()).get();
                producto.setCantidad(producto.getCantidad() - lista.get(i).getCantidad_total());
                repositorioPo.save(producto);
            }
        }else{
            System.out.println("NO SIRVIO...");
        }
        lista.clear();
        return "redirect:/Gestion_Inventarios/Productos/";
    }
}
