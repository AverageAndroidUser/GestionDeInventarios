package com.example.demo.Entidades.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.usuario.Usuario;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer>{

    List<Pedido> findByUsuario(Usuario usuario);
    
}
