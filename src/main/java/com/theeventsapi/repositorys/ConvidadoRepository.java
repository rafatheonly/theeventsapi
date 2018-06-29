package com.theeventsapi.repositorys;

//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.theeventsapi.entitys.Convidado;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {

	Page<Convidado> findByEventoIdOrderByNomeDesc(Long eventoId, Pageable pages);
	
	//List<Convidado> findByEventoIdOrderByNomeDesc(Long eventoId);
	
	@Query("select count(c.id) from Convidado c")
    Long findCount();
}
