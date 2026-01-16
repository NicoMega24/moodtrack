package com.demo.spring.moodtrack.service.usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.demo.spring.moodtrack.dto.usuario.UsuarioCreateDto;
import com.demo.spring.moodtrack.dto.usuario.UsuarioDto;
import com.demo.spring.moodtrack.dto.usuario.UsuarioResumenDto;

public interface UsuarioService {
    List<UsuarioDto> obtenerTodos(String nombre, String email, String colorFavorito);
    Optional<UsuarioDto> obtenerPorId(UUID id);
    UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto);
    UsuarioDto updateUsuario(UUID id, UsuarioCreateDto usuarioCreateDto);
    boolean eliminarUsuario(UUID id);
    UsuarioResumenDto obtenerResumenUsuario(UUID id);
}