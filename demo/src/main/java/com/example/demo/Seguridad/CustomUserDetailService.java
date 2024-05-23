package com.example.demo.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.Entidades.usuario.Usuario;
import com.example.demo.Entidades.usuario.UsuarioRepositorio;

public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    UsuarioRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repositorio.findByNom_usuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("No se encontro al usuario");
        }
        return new CustomUserDetails(usuario);
    }
    
}
