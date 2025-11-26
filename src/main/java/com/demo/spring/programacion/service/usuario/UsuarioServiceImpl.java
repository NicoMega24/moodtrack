package com.demo.spring.programacion.service.usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.programacion.dto.usuario.UsuarioCreateDto;
import com.demo.spring.programacion.dto.usuario.UsuarioDto;
import com.demo.spring.programacion.mapper.usuario.UsuarioMapper;
import com.demo.spring.programacion.model.Usuario;
import com.demo.spring.programacion.repository.usuario.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

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
}