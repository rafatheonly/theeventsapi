package com.theeventsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theeventsapi.entitys.Tipoevento;

public interface TipoeventoRepository extends JpaRepository<Tipoevento, Long> {

}
