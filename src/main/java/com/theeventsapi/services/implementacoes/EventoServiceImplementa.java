package com.theeventsapi.services.implementacoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.theeventsapi.entitys.Convidado;
import com.theeventsapi.entitys.Evento;
import com.theeventsapi.repositorys.ConvidadoRepository;
import com.theeventsapi.repositorys.EventoRepository;
import com.theeventsapi.services.EventoService;

@Component
public class EventoServiceImplementa implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private ConvidadoRepository convidadoRepository;

	public List<Evento> findAll() {
		return this.eventoRepository.findAll();
	}

	public Evento createOrUpdate(Evento evento) {
		return this.eventoRepository.save(evento);
	}

	public Evento findById(Long id) {
		return this.eventoRepository.findById(id).get();
	}

	public void delete(Long id) {
		this.eventoRepository.findById(id).get();
	}

	public Page<Evento> findAllPage(int pagina, int quantidade) {
		PageRequest pageRequest = PageRequest.of(pagina, quantidade);
		return this.eventoRepository.findAll(pageRequest);
	}

	public Iterable<Convidado> listConvidados(Long eventoId) {
		return this.convidadoRepository.findByEventoIdOrderByNomeDesc(eventoId);
	}

}
