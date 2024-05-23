package com.example.demo.Entidades.transaccion;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.Entidades.producto.Producto;
import com.example.demo.Entidades.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaccion {

    @Id private int ID_Transaccion;
    
    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    @ManyToOne @JoinColumn(name = "ID_Producto")
    private Producto producto;

    private int Cantidad_total;
    private int Precio_total;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date Fecha_entrega;

    
    private Date Fecha_pedido;

    public Transaccion() {
        super();
    }

    public Transaccion(Usuario usuario, Producto producto){
        this.usuario = usuario;
        this.producto = producto;
    }

    public Transaccion(int iD_Transaccion, Usuario usuario, Producto producto, int cantidad_total, int precio_total,
            Date fecha_entrega, Date fecha_pedido) {
        ID_Transaccion = iD_Transaccion;
        this.usuario = usuario;
        this.producto = producto;
        Cantidad_total = cantidad_total;
        Precio_total = precio_total;
        Fecha_entrega = fecha_entrega;
        Fecha_pedido = fecha_pedido;
    }

    public int getID_Transaccion() {
        return ID_Transaccion;
    }

    public void setID_Transaccion(int iD_Transaccion) {
        ID_Transaccion = iD_Transaccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public int getPrecio_total() {
        return Precio_total;
    }

    public void setPrecio_total(int precio_total) {
        Precio_total = precio_total;
    }

    public Date getFecha_entrega() {
        return Fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        Fecha_entrega = fecha_entrega;
    }

    public Date getFecha_pedido() {
        return Fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        Fecha_pedido = fecha_pedido;
    }

    

    
    
}
