package com.theeventsapi.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.theeventsapi.entitys.Evento;

@Component
public interface EventoService {

	Evento findAll();
	
	Evento createOrUpdate(Evento evento);

	Evento findById(Long id);	

	// PASSA A pagina Q SE QUER E A quantidade DE REGISTROS
	Page<Evento> findAll(int pagina, int quantidade);
}
