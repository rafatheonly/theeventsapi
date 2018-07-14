package com.theeventsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.theeventsapi.entitys.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

	Evento findByTitulo(String titulo);
	
	@Query("select count(e.id) from Evento e")
    Long findCount();
}
