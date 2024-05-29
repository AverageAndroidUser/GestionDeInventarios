package com.example.demo.Entidades.transaccion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.usuario.Usuario;

@Repository
public interface TransaccionRepositorio extends JpaRepository<Transaccion, Integer>{

    List<Transaccion> findByUsuario(Usuario usuario);

    @Query("SELECT t FROM Transaccion t JOIN t.producto p WHERE p.proveedor = ?1")
    Page<Transaccion> findByProveedor(Usuario usuario, Pageable pageable);

    @Query("SELECT t FROM Transaccion t WHERE t.usuario = ?1 AND t.Tipotransaccion = ?2")
    Page<Transaccion> findByUsuario(Usuario usuario, int Tipotransaccion, Pageable pageable);
}
