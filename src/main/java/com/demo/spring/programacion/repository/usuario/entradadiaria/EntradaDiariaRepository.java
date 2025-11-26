package com.demo.spring.programacion.repository.usuario.entradadiaria;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.programacion.model.EntradaDiaria;

public interface EntradaDiariaRepository extends JpaRepository<EntradaDiaria, Long> {

}