package com.demo.spring.programacion.service.usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
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
        return UsuarioMapper.toDtoList(usuarioList);
    }

    @Override
    public Optional<UsuarioDto> obtenerPorId(UUID id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDto);
    }

    @Override
    public UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioCreateDto);
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioResumenDto obtenerResumenUsuario(UUID id) {

        log.info("Construyendo resumen para el usuario {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        String nombre = usuario.getNombre();
        String email = usuario.getEmail();
        String colorFavorito = usuario.getPerfil().getColorFavorito();

        Long cantidadEntradas = usuario.getEntradasDiarias().stream().count();

        LocalDate ultimaEntrada = usuario.getEntradasDiarias().stream()
                .map(e -> e.getFecha())
                .max(LocalDate::compareTo)
                .orElse(null);

        return new UsuarioResumenDto(
                nombre,
                email,
                colorFavorito,
                cantidadEntradas,
                ultimaEntrada
        );
    }
}
