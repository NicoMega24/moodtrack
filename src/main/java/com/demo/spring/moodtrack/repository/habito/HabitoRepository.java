package com.demo.spring.moodtrack.repository.habito;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.moodtrack.model.Habito;

public interface HabitoRepository extends JpaRepository<Habito, Long> {

}