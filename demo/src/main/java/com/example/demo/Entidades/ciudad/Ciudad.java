package com.example.demo.Entidades.ciudad;

import com.example.demo.Entidades.departamento.Departamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Ciudad;
    private String Nombre_Ciu;

    @ManyToOne
    @JoinColumn(name = "ID_Departamento")
    Departamento departamento;

    public Ciudad() {
        super();
    }

    public Ciudad(int iD_Ciudad, String NombreCiu, Departamento departamento) {
        ID_Ciudad = iD_Ciudad;
        this.Nombre_Ciu = NombreCiu;
        this.departamento = departamento;
    }

    public int getID_Ciudad() {
        return ID_Ciudad;
    }

    public void setID_Ciudad(int iD_Ciudad) {
        ID_Ciudad = iD_Ciudad;
    }

    public String getNombreCiu() {
        return Nombre_Ciu;
    }

    public void setNombreCiu(String NombreCiu) {
        this.Nombre_Ciu = NombreCiu;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }  
}
