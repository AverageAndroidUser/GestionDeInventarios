package com.example.demo.Entidades.transaccion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.usuario.Usuario;

@Repository
public interface TransaccionRepositorio extends JpaRepository<Transaccion, Integer>{

    List<Transaccion> findByUsuario(Usuario usuario);

    @Query("SELECT t FROM Transaccion t JOIN t.producto p WHERE p.proveedor.ID_Usuario = ?1 AND t.Tipotransaccion > ?2")
    List<Transaccion> findByProveedor(int ID_Usuario, int tipo);

}
