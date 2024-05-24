package com.example.demo.Controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.usuarioLog;
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

    @GetMapping("/")
    public String nuevoPedido(Model model){
        List<Detalle_pedido> lista = new ArrayList<>();
        model.addAttribute("Pedidoo", repositorio.findByUsuario(usuarioLog.nombreUsuario()));
        model.addAttribute("Productoss", repositorioPo.findByUsuario(usuarioLog.nombreUsuario()));
        model.addAttribute("Detaless", lista);
        return "";
    }

    //CREAR METODO QUE GENERE UN DETALLE PEDIDO CON LOS DATOS DEL PRODUCTO QUE SE SELEECCIONAN DESDE EL HTML    

        @GetMapping("/Gestion_Inventarios/Pedido/DetallePedido/{id}")
        public void nuevoDetalle(@PathVariable int id){
            Detalle_pedido detalle = new Detalle_pedido();
            detalle.setProducto(repositorioPo.findById(id).get());
        }
}
