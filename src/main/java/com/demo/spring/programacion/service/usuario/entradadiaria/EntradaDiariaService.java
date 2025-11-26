package com.demo.spring.programacion.service.usuario.entradadiaria;

import com.demo.spring.programacion.dto.entradadiaria.EntradaDiariaCreateDto;
import com.demo.spring.programacion.dto.entradadiaria.EntradaDiariaDto;

public interface EntradaDiariaService {
    EntradaDiariaDto create(EntradaDiariaCreateDto createDto);
}