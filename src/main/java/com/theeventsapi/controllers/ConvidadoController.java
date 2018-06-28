package com.theeventsapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theeventsapi.entitys.Convidado;
import com.theeventsapi.services.ConvidadoService;

@RestController
@RequestMapping("/convidados")
@CrossOrigin("${origem-permitida}")
public class ConvidadoController {

	@Autowired
	private ConvidadoService convidadoService;
	
	@GetMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<Convidado> findAll() {
		return convidadoService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<Convidado> findById(@PathVariable("id") Long id) {
		return convidadoService.findByEventoIdOrderByNomeDesc(id);
	}
}
