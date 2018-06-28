package com.theeventsapi.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.theeventsapi.entitys.Convidado;

@Component
public interface ConvidadoService {

	List<Convidado> findAll();
	
	List<Convidado> findByEventoIdOrderByNomeDesc(Long eventoId);
}
