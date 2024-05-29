package com.example.demo.Entidades.producto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entidades.usuario.Usuario;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{

    Page<Producto> findByUsuario(Usuario usuario, Pageable page);

    @Query("SELECT p FROM Producto p JOIN p.usuario u WHERE u.ID_Usuario = ?1 AND p.Cantidad > 0")
    List<Producto> findByUsuario2(int usuario);

    @Query("SELECT p FROM Producto p JOIN p.usuario u WHERE u.tipousuario = ?1 AND p.Cantidad > ?2")
    List<Producto> findByCantidad(int tipo, int Cantidad);

    @Query("SELECT p FROM Producto p WHERE p.Nombre LIKE %?1% AND p.usuario = ?2")
    List<Producto> findByNombreAndUsuario(String nombre, Usuario usuario);

    @Query("SELECT p FROM Producto p WHERE p.proveedor.Nom_usuario = ?1 AND p.usuario = ?2")
    List<Producto> findByProveedorAndUsuario(String proveedor, Usuario usuario);
}
