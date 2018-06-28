package com.theeventsapi.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.theeventsapi.entitys.Convidado;
import com.theeventsapi.entitys.Evento;

@Component
public interface EventoService {

	List<Evento> findAll();
	
	Evento createOrUpdate(Evento evento);

	Evento findById(Long id);	
	
	Iterable<Convidado> listConvidados(Long eventoId);

	// PASSA A pagina Q SE QUER E A quantidade DE REGISTROS
	Page<Evento> findAllPage(int pagina, int quantidade);
}
