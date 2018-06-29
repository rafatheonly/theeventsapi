package com.theeventsapi.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.theeventsapi.entitys.Convidado;

@Component
public interface ConvidadoService {

	List<Convidado> findAll();

	Convidado createOrUpdate(Convidado convidado);

	//List<Convidado> findByEventoIdOrderByNomeDesc(Long eventoId);

	Long findCount();
	
	Page<Convidado> findByEventoIdOrderByNomeDesc(int page, int count, Long eventoId);
}
