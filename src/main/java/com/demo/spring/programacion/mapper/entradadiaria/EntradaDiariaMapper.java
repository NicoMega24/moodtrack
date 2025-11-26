package com.demo.spring.programacion.mapper.entradadiaria;

import com.demo.spring.programacion.dto.entradadiaria.EntradaDiariaDto;
import com.demo.spring.programacion.mapper.usuario.UsuarioMapper;
import com.demo.spring.programacion.model.EntradaDiaria;

public final class EntradaDiariaMapper {
    public EntradaDiariaMapper() {}

    public static EntradaDiariaDto toDto(EntradaDiaria entradaDiaria) {
        if(entradaDiaria == null) return null;

        EntradaDiariaDto dto = new EntradaDiariaDto();
        dto.setId(entradaDiaria.getId());
        dto.setFecha(entradaDiaria.getFecha());
        dto.setEmocion(entradaDiaria.getEmocion());
        dto.setReflexion(entradaDiaria.getReflexion());
        dto.setUsuarioDto(UsuarioMapper.toDto( entradaDiaria.getUsuario() )  );

        if ( entradaDiaria.getHabitos() != null && !entradaDiaria.getHabitos().isEmpty()){
            dto.setHabitosDescripciones(
                    entradaDiaria.getHabitos().stream()
                            .map(habito -> habito.getDescripcion())
                            .toList()
            );
        }

        return dto;
    }

}