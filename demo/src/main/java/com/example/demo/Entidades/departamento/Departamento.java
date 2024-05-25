package com.example.demo.Entidades.departamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Departamento;
    private String Nombre;

    public Departamento(){
        super();
    }

    public Departamento(int ID_Departamento, String Nombre){
        this.ID_Departamento = ID_Departamento;
        this.Nombre = Nombre; 
    }

    public int getID_Departamento() {
        return ID_Departamento;
    }

    public void setID_Departamento(int iD_Departamento) {
        ID_Departamento = iD_Departamento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    
}
