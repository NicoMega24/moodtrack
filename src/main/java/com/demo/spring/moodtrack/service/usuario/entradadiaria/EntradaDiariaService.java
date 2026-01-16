package com.demo.spring.moodtrack.service.usuario.entradadiaria;

import com.demo.spring.moodtrack.dto.entradadiaria.EntradaDiariaCreateDto;
import com.demo.spring.moodtrack.dto.entradadiaria.EntradaDiariaDto;

public interface EntradaDiariaService {
    EntradaDiariaDto create(EntradaDiariaCreateDto createDto);
}