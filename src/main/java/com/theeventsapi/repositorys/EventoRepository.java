package com.theeventsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theeventsapi.entitys.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

	Evento findByTitulo(String titulo);
}
