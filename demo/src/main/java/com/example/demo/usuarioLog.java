package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.Entidades.usuario.Usuario;
import com.example.demo.Entidades.usuario.UsuarioRepositorio;

@Component //Especifica que es un componente, entonces puedo usar su metodo y cuando se llame a la clase se declara como un repositorio con el @Autowired
public class usuarioLog {

    @Autowired UsuarioRepositorio repositorio;
    
    public Usuario nombreUsuario(){
        Object usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String Nombre_usuario;
        if(usuario instanceof UserDetails){
            Nombre_usuario = (((UserDetails) usuario).getUsername());
        }else{
            Nombre_usuario = usuario.toString();
        }
        Usuario usuarioP = repositorio.findByNom_usuario(Nombre_usuario);
        return usuarioP;
    }

    
}
