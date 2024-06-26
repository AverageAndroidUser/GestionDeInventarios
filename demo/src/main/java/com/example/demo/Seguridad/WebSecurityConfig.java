package com.example.demo.Seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig{
    
    @Bean
    UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.authenticationProvider(authenticationProvider());

        http.authorizeHttpRequests(auth -> 
            auth.requestMatchers("/Gestion_Inventarios/guardarUsuario", "/Ciudades/").permitAll()
            .requestMatchers("/Gestion_Inventarios/**").authenticated()
            .anyRequest().permitAll()
            )
            .formLogin(login -> 
                login.loginPage("/Gestion_Inventarios/register")
                .loginProcessingUrl("/login")
                .failureUrl("/Gestion_Inventarios/error")
                .usernameParameter("Nom_usuario")
                .passwordParameter("Contraseña")
                .defaultSuccessUrl("/Gestion_Inventarios/Productos/0")
                .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/Gestion_Inventarios/register").permitAll()
            ).exceptionHandling().accessDeniedPage("/error");
            return http.build();
    }
}
