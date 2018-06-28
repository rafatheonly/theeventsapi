package com.theeventsapi.services.implementacoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theeventsapi.entitys.Convidado;
import com.theeventsapi.repositorys.ConvidadoRepository;
import com.theeventsapi.services.ConvidadoService;

@Component
public class ConvidadoServiceImplementa implements ConvidadoService {
	
	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	public List<Convidado> findByEventoIdOrderByNomeDesc(Long eventoId) {		
		return this.convidadoRepository.findByEventoIdOrderByNomeDesc(eventoId);
	}
	
	public List<Convidado> findAll() {		
		return convidadoRepository.findAll();
	}
	
}
