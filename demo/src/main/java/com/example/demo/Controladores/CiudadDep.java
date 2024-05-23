package com.example.demo.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Entidades.ciudad.CiudadRepositorio;
import com.example.demo.Entidades.departamento.Departamento;
import com.example.demo.Entidades.departamento.DepartamentoRepositorio;

import com.example.demo.Entidades.ciudad.Ciudad;

@Controller
@RequestMapping("/Ciudades")
public class CiudadDep {
    
    @Autowired
    CiudadRepositorio repositorio;
    DepartamentoRepositorio repositorioDe;

    @GetMapping("/{id}")
    @ResponseBody
    public List<Ciudad> ciudadesDept(@PathVariable int id){
        Departamento departamento = new Departamento();
        departamento.setID_Departamento(id);
        return repositorio.findByDepartamento(departamento);
    }
}
