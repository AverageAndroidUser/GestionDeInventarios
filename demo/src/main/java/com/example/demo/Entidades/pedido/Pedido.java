package com.example.demo.Entidades.pedido;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.Entidades.pedido.detalle_pedido.Detalle_pedido;
import com.example.demo.Entidades.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
    
    @Id private int ID_pedido;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date Fecha_entrega;

    private Date Fecha_salida;

    private int Estado;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    //private List<Detalle_pedido> detalle;

    public Pedido() {
        super();
    }

    public Pedido(int iD_pedido, Date fecha_entrega, Date fecha_salida, int estado, Usuario usuario) {
        ID_pedido = iD_pedido;
        Fecha_entrega = fecha_entrega;
        Fecha_salida = fecha_salida;
        Estado = estado;
        this.usuario = usuario;
    }

    public int getID_pedido() {
        return ID_pedido;
    }

    public void setID_pedido(int iD_pedido) {
        ID_pedido = iD_pedido;
    }

    public Date getFecha_entrega() {
        return Fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        Fecha_entrega = fecha_entrega;
    }

    public Date getFecha_salida() {
        return Fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        Fecha_salida = fecha_salida;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*public List<Detalle_pedido> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalle_pedido> detalle) {
        this.detalle = detalle;
    }*/
    
}
