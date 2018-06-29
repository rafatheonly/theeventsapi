package com.theeventsapi.services.implementacoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.theeventsapi.entitys.Convidado;
import com.theeventsapi.repositorys.ConvidadoRepository;
import com.theeventsapi.services.ConvidadoService;

@Component
public class ConvidadoServiceImplementa implements ConvidadoService {
	
	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	/**public List<Convidado> findByEventoIdOrderByNomeDesc(Long eventoId) {		
		return this.convidadoRepository.findByEventoIdOrderByNomeDesc(eventoId);
	}**/
	
	public List<Convidado> findAll() {		
		return convidadoRepository.findAll();
	}
	
	public Convidado createOrUpdate(Convidado convidado) {
		return this.convidadoRepository.save(convidado);
	}
	
	public Long findCount() {		
		return this.convidadoRepository.findCount();
	}
	
	public Page<Convidado> findByEventoIdOrderByNomeDesc(int pagina, int quantidade, Long eventoId) {	
		PageRequest pageRequest = PageRequest.of(pagina, quantidade);
		return this.convidadoRepository.findByEventoIdOrderByNomeDesc(eventoId, pageRequest);
	}
	
}
