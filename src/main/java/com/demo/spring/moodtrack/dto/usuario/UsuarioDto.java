package com.demo.spring.moodtrack.dto.usuario;

import java.util.UUID;

import com.demo.spring.moodtrack.dto.perfil.PerfilUsuarioDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private UUID id;
    private String nombre;
    private String email;
    private PerfilUsuarioDto perfilUsuarioDto;

}