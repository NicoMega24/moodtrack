package com.demo.spring.programacion.mapper.usuario;

import java.util.List;

import com.demo.spring.programacion.dto.usuario.UsuarioCreateDto;
import com.demo.spring.programacion.dto.usuario.UsuarioDto;
import com.demo.spring.programacion.mapper.perfil.PerfilMapper;
import com.demo.spring.programacion.model.Usuario;

public final class UsuarioMapper {
    private UsuarioMapper() {}

    public static UsuarioDto toDto(Usuario usuario){

        if(usuario == null) return null;

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setPerfilUsuarioDto(PerfilMapper.toDto(usuario.getPerfil()));
        return usuarioDto;

    }

    public static List<UsuarioDto> toDtoList(List<Usuario> usuarios){
        return usuarios.stream()
                .map(usuario -> toDto(usuario))
                .toList();
    }


    public static Usuario toEntity(UsuarioCreateDto usuarioCreateDto){
        if(usuarioCreateDto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setEmail( usuarioCreateDto.getEmail() );
        usuario.setNombre( usuarioCreateDto.getNombre() );
        usuario.setPerfil( PerfilMapper.toEntity( usuarioCreateDto.getPerfilUsuarioDto() ) );
        usuario.setEntradasDiarias( List.of() );
        return usuario;

    }


}