package com.theeventsapi.controllers;

import java.io.InputStream;
import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.theeventsapi.entitys.Convidado;
import com.theeventsapi.entitys.Evento;
import com.theeventsapi.responses.Response;
import com.theeventsapi.services.EventoService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/eventos")
@CrossOrigin("${origem-permitida}")
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Evento>> create(HttpServletRequest request, @RequestBody Evento evento,
			BindingResult result) {
		Response<Evento> response = new Response<Evento>();
		try {
			validateCreateEvento(evento, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			evento.setId(eventoService.findCount() + 1L);
			evento.setAtivo(true);
			Evento eventoPersisted = (Evento) eventoService.createOrUpdate(evento);
			response.setData(eventoPersisted);
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("Evento já cadastrado!");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateEvento(Evento evento, BindingResult result) {
		if (evento.getTitulo() == null) {
			result.addError(new ObjectError("Evento", "Titulo do evento não informado!"));
			return;
		}
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Evento>> update(HttpServletRequest request, @RequestBody Evento evento,
			BindingResult result) {
		Response<Evento> response = new Response<Evento>();
		try {
			validateUpdate(evento, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Evento eventoPersisted = (Evento) eventoService.createOrUpdate(evento);
			response.setData(eventoPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateUpdate(Evento evento, BindingResult result) {
		if (evento.getId() == 0) {
			result.addError(new ObjectError("Evento", "Id não informado!"));
			return;
		}
		if (evento.getTitulo() == null) {
			result.addError(new ObjectError("Evento", "Email não informado!"));
			return;
		}
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Evento>> findById(@PathVariable("id") long id) {
		Response<Evento> response = new Response<Evento>();
		Evento evento = eventoService.findById(id);
		if (evento == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(evento);
		return ResponseEntity.ok(response);
	}

	/**
	 * @GetMapping(value = "{id}") @PreAuthorize("hasAnyRole('ADMIN')") public
	 *                   ResponseEntity<Response<Evento>>
	 *                   findById(@PathVariable("id") Long id) { Response<Evento>
	 *                   response = new Response<Evento>(); Evento evento =
	 *                   eventoService.findById(id); if (evento == null) {
	 *                   response.getErrors().add("Register not found id:" + id);
	 *                   return ResponseEntity.badRequest().body(response); }
	 *                   List<Convidado> convidados = new ArrayList<Convidado>();
	 *                   Iterable<Convidado> changesCurrent =
	 *                   eventoService.listConvidados(evento.getId()); for
	 *                   (Iterator<Convidado> iterator = changesCurrent.iterator();
	 *                   iterator.hasNext();) { Convidado convidado = (Convidado)
	 *                   iterator.next(); System.out.println(convidado);
	 *                   convidado.setEvento(null); convidados.add(convidado); }
	 *                   evento.setConvidado(convidados); response.setData(evento);
	 *                   return ResponseEntity.ok(response); }
	 **/

	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Evento>>> findAllPage(@PathVariable int page, @PathVariable int count) {
		Response<Page<Evento>> response = new Response<Page<Evento>>();
		Page<Evento> eventos = eventoService.findAllPage(page, count);
		response.setData(eventos);
		return ResponseEntity.ok(response);
	}

	@GetMapping()
	public List<Evento> findAll() {
		return eventoService.findAll();
	}
	
	@GetMapping()		
		@RequestMapping("/exportevento")
		public ResponseEntity<byte[]> exportEvento() throws JRException {
			 List<Evento> eventos = eventoService.findAll();    //usuarioRepository.findAll();
			 Map<String, Object> parametros = new HashMap<>();
			 InputStream x = getClass().getResourceAsStream("/reports/eventoExport.jrxml");
			 JasperReport is = JasperCompileManager.compileReport(x);
	
			 JasperPrint print = JasperFillManager.fillReport(is, parametros, new JRBeanCollectionDataSource(eventos));
	
			 return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(JasperExportManager.exportReportToPdf(print));
		}
}
