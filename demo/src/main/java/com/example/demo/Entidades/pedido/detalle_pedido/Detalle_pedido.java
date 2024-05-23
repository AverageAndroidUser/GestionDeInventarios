package com.example.demo.Entidades.pedido.detalle_pedido;

import com.example.demo.Entidades.pedido.Pedido;
import com.example.demo.Entidades.producto.Producto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Detalle_pedido {

    @Id private int ID_Detalle_Pedido;

    @ManyToOne
    @JoinColumn(name = "ID_Pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "ID_Producto")
    private Producto producto;

    private int Cantidad_total;

    public Detalle_pedido() {
        super();
    }

    public Detalle_pedido(int iD_Detalle_Pedido, Pedido pedido, Producto producto, int cantidad_total) {
        ID_Detalle_Pedido = iD_Detalle_Pedido;
        this.pedido = pedido;
        this.producto = producto;
        Cantidad_total = cantidad_total;
    }

    public int getID_Detalle_Pedido() {
        return ID_Detalle_Pedido;
    }

    public void setID_Detalle_Pedido(int iD_Detalle_Pedido) {
        ID_Detalle_Pedido = iD_Detalle_Pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad_total() {
        return Cantidad_total;
    }

    public void setCantidad_total(int cantidad_total) {
        Cantidad_total = cantidad_total;
    }
    
    
    
}
