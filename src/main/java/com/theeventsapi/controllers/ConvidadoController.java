package com.theeventsapi.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theeventsapi.entitys.Convidado;
//import com.theeventsapi.entitys.Evento;
import com.theeventsapi.responses.Response;
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

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Convidado>> create(HttpServletRequest request, @RequestBody Convidado convidado,
			BindingResult result) {
		Response<Convidado> response = new Response<Convidado>();
		try {
			validateCreateEvento(convidado, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			convidado.setId(convidadoService.findCount() + 1L);
			Convidado convidadoPersisted = (Convidado) convidadoService.createOrUpdate(convidado);
			response.setData(convidadoPersisted);
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("Evento já cadastrado!");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateEvento(Convidado convidado, BindingResult result) {
		if (convidado.getNome() == null) {
			result.addError(new ObjectError("Convidado", "O nome do convidado não informado!"));
			return;
		}
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Convidado>> update(HttpServletRequest request, @RequestBody Convidado convidado,
			BindingResult result) {
		Response<Convidado> response = new Response<Convidado>();
		try {
			validateUpdate(convidado, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Convidado convidadoPersisted = (Convidado) convidadoService.createOrUpdate(convidado);
			response.setData(convidadoPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateUpdate(Convidado convidado, BindingResult result) {
		if (convidado.getNome() == null) {
			result.addError(new ObjectError("Convidado", "O nome do convidado não informado!"));
			return;
		}
	}

	@GetMapping(value = "{page}/{count}/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Convidado>>> findAllPage(@PathVariable int page, @PathVariable int count, @PathVariable Long id) {
		Response<Page<Convidado>> response = new Response<Page<Convidado>>();
		Page<Convidado> convidados = convidadoService.findByEventoIdOrderByNomeDesc(page, count, id);
		response.setData(convidados);
		return ResponseEntity.ok(response);
	}
	
	/**@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<Convidado> findById(@PathVariable("id") Long id) {
		return convidadoService.findByEventoIdOrderByNomeDesc(id);
	}**/
	
	/**@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<List<Convidado>>> findById(@PathVariable("id") Long id) {
		Response<List<Convidado>> response = new Response<List<Convidado>>();
		List<Convidado> convidados = convidadoService.findByEventoIdOrderByNomeDesc(id);
		response.setData(convidados);
		return ResponseEntity.ok(response);
	}**/
}
