package com.demo.spring.moodtrack.repository.usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.spring.moodtrack.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, JpaSpecificationExecutor<Usuario> {
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAllByNombreStartingWith(String nombre);

    List<Usuario> findAllByNombreEqualsIgnoreCase(String nombre);

    List<Usuario> findAllByEmailContainingIgnoreCase(String email);

    List<Usuario> findAllByEmailContainingIgnoreCaseAndNombreEqualsIgnoreCase(String email, String nombre);
}