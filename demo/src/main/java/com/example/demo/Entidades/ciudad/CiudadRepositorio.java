package com.example.demo.Entidades.ciudad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.departamento.Departamento;

@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad, Integer>{
    List<Ciudad> findByDepartamento(Departamento departamento);
}
