package com.example.demo.Entidades.producto;

import com.example.demo.Entidades.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Producto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Producto;
    private String Nombre;
    private String Descripcion;
    private int Cantidad;
    private int Preciounitario;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Proveedor")
    private Usuario proveedor;

    public Producto() {
        super();
    }

    public Producto(int iD_Producto, String nombre, String descripcion, int cantidad, int precioUnitario,
            Usuario usuario, Usuario proveedor) {
        ID_Producto = iD_Producto;
        Nombre = nombre;
        Descripcion = descripcion;
        this.Cantidad = cantidad;
        Preciounitario = precioUnitario;
        this.usuario = usuario;
        this.proveedor = proveedor;
    }

    

    public Producto(String nombre, String descripcion, int cantidad, int preciounitario, Usuario usuario,
            Usuario proveedor) {
        Nombre = nombre;
        Descripcion = descripcion;
        Cantidad = cantidad;
        Preciounitario = preciounitario;
        this.usuario = usuario;
        this.proveedor = proveedor;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int iD_Producto) {
        ID_Producto = iD_Producto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        this.Cantidad = cantidad;
    }

    public int getPreciounitario() {
        return Preciounitario;
    }

    public void setPreciounitario(int precioUnitario) {
        Preciounitario = precioUnitario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getProveedor() {
        return proveedor;
    }

    public void setProveedor(Usuario proveedor) {
        this.proveedor = proveedor;
    }
    
}
