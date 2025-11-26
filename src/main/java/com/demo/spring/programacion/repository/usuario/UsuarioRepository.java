package com.demo.spring.programacion.repository.usuario;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.programacion.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}