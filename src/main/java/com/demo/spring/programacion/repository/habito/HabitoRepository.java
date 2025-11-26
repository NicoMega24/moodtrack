package com.demo.spring.programacion.repository.habito;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.programacion.model.Habito;

public interface HabitoRepository extends JpaRepository<Habito, Long> {

}