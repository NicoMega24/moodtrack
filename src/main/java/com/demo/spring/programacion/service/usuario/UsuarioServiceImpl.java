package com.demo.spring.programacion.service.usuario;

import java.lang.System.Logger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.programacion.dto.usuario.UsuarioCreateDto;
import com.demo.spring.programacion.dto.usuario.UsuarioDto;
import com.demo.spring.programacion.dto.usuario.UsuarioResumenDto;
import com.demo.spring.programacion.mapper.usuario.UsuarioMapper;
import com.demo.spring.programacion.model.Usuario;
import com.demo.spring.programacion.repository.usuario.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {
        List<Usuario> usuarioList = usuarioRepository.findAll();

        return UsuarioMapper.toDtoList( usuarioList );
    }

    @Override
    public Optional<UsuarioDto> obtenerPorId(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioEntity = usuario.get();
            return Optional.of( UsuarioMapper.toDto(usuarioEntity) );
        }
        return Optional.empty();
    }

    @Override
    public UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuario = UsuarioMapper.toEntity( usuarioCreateDto );
        usuario = usuarioRepository.save( usuario );
        return UsuarioMapper.toDto( usuario );
    }

    @Override
    public UsuarioResumenDto obtenerResumenUsuario(Long id) {
        log.info("Construyendo resumen para el usuario {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Datos básicos
        String nombre = usuario.getNombre();
        String email = usuario.getEmail();
        String colorFavorito = usuario.getPerfilUsuario().getColorFavorito();

        // Cantidad de entradas
        Long cantidad = usuario.getEntradas().stream().count();

        // Última fecha
        var ultima = usuario.getEntradas().stream()
                .map(e -> e.getFecha())  // suponiendo que EntradaDiaria tiene getFecha()
                .max(LocalDate::compareTo)
                .orElse(null);

        return new UsuarioResumenDto(
                nombre,
                email,
                colorFavorito,
                cantidad,
                ultima
            );
        }
}