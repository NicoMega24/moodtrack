package com.demo.spring.programacion.service.usuario.entradadiaria.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.demo.spring.programacion.dto.entradadiaria.EntradaDiariaCreateDto;
import com.demo.spring.programacion.dto.entradadiaria.EntradaDiariaDto;
import com.demo.spring.programacion.mapper.entradadiaria.EntradaDiariaMapper;
import com.demo.spring.programacion.model.EntradaDiaria;
import com.demo.spring.programacion.model.Habito;
import com.demo.spring.programacion.model.Usuario;
import com.demo.spring.programacion.repository.habito.HabitoRepository;
import com.demo.spring.programacion.repository.usuario.UsuarioRepository;
import com.demo.spring.programacion.repository.usuario.entradadiaria.EntradaDiariaRepository;
import com.demo.spring.programacion.service.usuario.entradadiaria.EntradaDiariaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntradaDiariaServiceImpl implements EntradaDiariaService {

    private final EntradaDiariaRepository entradaDiariaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HabitoRepository habitoRepository;

    @Override
    public EntradaDiariaDto create(EntradaDiariaCreateDto createDto) {
        log.info("Creando entrada Diaria");
        UUID uuidUsuario = createDto.getUsuarioId();

        Optional<Usuario> usuario = usuarioRepository.findById(uuidUsuario);

        if(usuario.isEmpty()){
            log.warn("Usuario no encontrado");
            throw new IllegalArgumentException("Usuario no encontrado id : " + uuidUsuario);
        }

        List<Habito> habitos = List.of();
        if(createDto.getHabitosIds() != null && !createDto.getHabitosIds().isEmpty()){
            habitos = habitoRepository.findAllById( createDto.getHabitosIds() );
            if(habitos.size() != createDto.getHabitosIds().size()){
                log.warn("Alguno de los habitos no se ha encontrado");
            }
        }

        EntradaDiaria entradaDiaria = new EntradaDiaria();
        entradaDiaria.setUsuario(usuario.get());
        entradaDiaria.setHabitos(habitos);
        entradaDiaria.setFecha(createDto.getFecha());
        entradaDiaria.setReflexion(createDto.getReflexion());
        entradaDiaria.setEmocion(createDto.getEmocion());

        EntradaDiaria saved = entradaDiariaRepository.save(entradaDiaria);

        log.info("Entrada guardado exitosamente");
        return EntradaDiariaMapper.toDto( saved );
    }

}