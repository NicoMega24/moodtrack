package com.demo.spring.moodtrack.repository.usuario.entradadiaria;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.moodtrack.model.EntradaDiaria;

public interface EntradaDiariaRepository extends JpaRepository<EntradaDiaria, Long> {

}