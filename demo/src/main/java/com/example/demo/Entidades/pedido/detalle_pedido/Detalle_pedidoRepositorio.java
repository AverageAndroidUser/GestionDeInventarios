package com.example.demo.Entidades.pedido.detalle_pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalle_pedidoRepositorio extends JpaRepository<Detalle_pedido, Integer>{
    
}
