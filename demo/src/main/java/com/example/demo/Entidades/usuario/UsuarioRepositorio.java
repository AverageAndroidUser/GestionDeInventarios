package com.example.demo.Entidades.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    @Query("SELECT u FROM Usuario u WHERE u.Nom_usuario = ?1")
    public Usuario findByNom_usuario(String Nombre_usuario);

    @Query("SELECT pro FROM Producto p JOIN p.proveedor pro WHERE p.usuario = ?1 GROUP BY pro")
    List<Usuario> findByUsuarioProveedor(Usuario usuario);

    @Query("SELECT pro FROM Producto p JOIN p.proveedor pro WHERE p.cantidad > 0 AND pro.tipousuario = 2 GROUP BY pro")
    List<Usuario>findAllProveedor();

    @Query("SELECT pro FROM Transaccion t JOIN t.producto p JOIN p.proveedor pro WHERE t.usuario = ?1 GROUP BY pro")
    List<Usuario> findAllProveedorTra(Usuario usuario);

    @Query("SELECT t.usuario FROM Transaccion t JOIN t.producto p WHERE p.proveedor = ?1 GROUP BY t.usuario")
    List<Usuario> findAllUsuarioTra(Usuario usuario);
}

