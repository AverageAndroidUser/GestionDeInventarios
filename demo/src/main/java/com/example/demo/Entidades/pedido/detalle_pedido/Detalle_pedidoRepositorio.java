package com.example.demo.Entidades.pedido.detalle_pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.usuario.Usuario;

@Repository
public interface Detalle_pedidoRepositorio extends JpaRepository<Detalle_pedido, Integer>{

    @Query("SELECT d FROM Detalle_pedido d JOIN d.pedido p JOIN d.producto WHERE p.usuario = ?1")
    Page<Detalle_pedido> findByUsuario(Usuario usuario, Pageable pageable);
    
}
