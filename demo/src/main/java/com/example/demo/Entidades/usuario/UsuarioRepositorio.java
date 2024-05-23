package com.example.demo.Entidades.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    @Query("SELECT u FROM Usuario u WHERE u.Nom_usuario = ?1")
    public Usuario findByNom_usuario(String Nombre_usuario);
}

