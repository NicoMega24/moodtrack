package com.demo.spring.moodtrack.dto.entradadiaria;

import java.time.LocalDate;
import java.util.List;

import com.demo.spring.moodtrack.dto.usuario.UsuarioDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntradaDiariaDto {

    private Long id;
    private LocalDate fecha;
    private String reflexion;
    private UsuarioDto usuarioDto;
    private String emocion;
    private List<String> habitosDescripciones;

}