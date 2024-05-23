package com.example.demo.Entidades.transaccion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.usuario.Usuario;

@Repository
public interface TransaccionRepositorio extends JpaRepository<Transaccion, Integer>{

    List<Transaccion> findByUsuario(Usuario usuario);
}
