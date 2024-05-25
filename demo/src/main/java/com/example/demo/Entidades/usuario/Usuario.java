package com.example.demo.Entidades.usuario;

import com.example.demo.Entidades.ciudad.Ciudad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Usuario;
    private int tipousuario;
    private String Nom_usuario;
    private String Contraseña;
    private String Nit;
    private String Nombre;
    private String Celular;
    private String Correo_Electronico;
    private String Codigo_Postal;

    @ManyToOne
    @JoinColumn(name = "ID_Ciudad")
    private Ciudad ciudad;

    public Usuario() {
        super();
    }

    public Usuario(int iD_Usuario, int tipo_Usuario, String nom_usuario, String contraseña, String nit, String nombre, String celular,
            String correo_Electronico, String codigo_Postal, Ciudad ciudad) {
        ID_Usuario = iD_Usuario;
        this.tipousuario = tipo_Usuario;
        Nom_usuario = nom_usuario;
        Contraseña = contraseña;
        Nit = nit;
        Nombre = nombre;
        Celular = celular;
        Correo_Electronico = correo_Electronico;
        Codigo_Postal = codigo_Postal;
        this.ciudad = ciudad;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int iD_Usuario) {
        ID_Usuario = iD_Usuario;
    }

    public int getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(int tipo_Usuario) {
        this.tipousuario = tipo_Usuario;
    }

    public String getNom_usuario() {
        return Nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        Nom_usuario = nom_usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String nit) {
        Nit = nit;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getCorreo_Electronico() {
        return Correo_Electronico;
    }

    public void setCorreo_Electronico(String correo_Electronico) {
        Correo_Electronico = correo_Electronico;
    }

    public String getCodigo_Postal() {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(String codigo_Postal) {
        Codigo_Postal = codigo_Postal;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    

    
}
