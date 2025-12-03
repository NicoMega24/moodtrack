package com.demo.spring.programacion.service.usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.demo.spring.programacion.dto.usuario.UsuarioCreateDto;
import com.demo.spring.programacion.dto.usuario.UsuarioDto;
import com.demo.spring.programacion.dto.usuario.UsuarioResumenDto;

public interface UsuarioService {
    List<UsuarioDto> obtenerTodos();
    Optional<UsuarioDto> obtenerPorId(UUID id);
    UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto);
    UsuarioResumenDto obtenerResumenUsuario(Long id);
}