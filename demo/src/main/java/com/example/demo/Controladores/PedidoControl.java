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

import com.example.demo.usuarioLog;
import com.example.demo.Entidades.pedido.Pedido;
import com.example.demo.Entidades.pedido.PedidoRepositorio;
import com.example.demo.Entidades.pedido.detalle_pedido.Detalle_pedido;
import com.example.demo.Entidades.pedido.detalle_pedido.Detalle_pedidoRepositorio;
import com.example.demo.Entidades.producto.ProductoRepositorio;

@Controller
@RequestMapping("/Gestion_Inventarios/Pedido")
public class PedidoControl {
    
    @Autowired PedidoRepositorio repositorio;
    @Autowired Detalle_pedidoRepositorio repositorioDe;
    @Autowired ProductoRepositorio repositorioPo;
    @Autowired usuarioLog usuarioLog;

   //Pedido pedido = new Pedido();
    List<Detalle_pedido> lista = new ArrayList<>();

    @GetMapping("/")
    public String nuevoPedido(Model model) {
        // List<Detalle_pedido> lista = new ArrayList<>();
        lista.clear();
        model.addAttribute("Pedidoo", new Pedido());
        model.addAttribute("Productoss", repositorioPo.findByUsuario(usuarioLog.nombreUsuario()));
        // model.addAttribute("Detalless", lista);
        return "Pedido/NuevoPedido";
    }

    //CREAR METODO QUE GENERE UN DETALLE PEDIDO CON LOS DATOS DEL PRODUCTO QUE SE SELEECCIONAN DESDE EL HTML    

    @GetMapping("/Gestion_Inventarios/Pedido/DetallePedido/{id_pedido}/{cantidad}")
    public void nuevoDetalle(@PathVariable int id_pedido, @PathVariable int cantidad) {
        Detalle_pedido detalle = new Detalle_pedido();
        detalle.setProducto(repositorioPo.findById(id_pedido).get());
        detalle.setCantidad_total(cantidad);
        lista.add(detalle);
    }

    @PostMapping("/Gestion_Inventarios/Pedido/Guardar")
    public String guardarPedido(Pedido pedido, Detalle_pedido detalle){
        pedido.setUsuario(usuarioLog.nombreUsuario());
        pedido.setFecha_salida(new Date());
        pedido.setEstado(1);
        Pedido pedido2 = repositorio.save(pedido);
        for(int i = 0; i < lista.size(); i++){
            lista.get(i).setPedido(pedido2);
            repositorioDe.save(lista.get(i));
        }
        lista.clear();
        return "";
    }
}
