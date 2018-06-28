package com.theeventsapi.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theeventsapi.entitys.Convidado;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {

	List<Convidado> findByEventoIdOrderByNomeDesc(Long eventoId);
}
