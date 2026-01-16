package com.demo.spring.moodtrack.service.habito;

import java.util.List;

import com.demo.spring.moodtrack.dto.habito.CreateHabitoDto;
import com.demo.spring.moodtrack.dto.habito.HabitoListDto;
import com.demo.spring.moodtrack.model.Habito;

public interface HabitoService {

    Habito crearHabito(CreateHabitoDto request);

    List<HabitoListDto> listarHabitos();
}
