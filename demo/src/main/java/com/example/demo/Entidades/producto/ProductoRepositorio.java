package com.example.demo.Entidades.producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.usuario.Usuario;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{
    List<Producto> findByUsuario(Usuario usuario);

    @Query("SELECT p FROM Producto p JOIN p.usuario u WHERE u.tipousuario = ?1 AND p.Cantidad > ?2")
    List<Producto> findByCantidad(int tipo, int Cantidad);
}
