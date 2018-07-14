package com.theeventsapi.controllers;

import java.io.InputStream;
import java.util.HashMap;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theeventsapi.entitys.Usuario;

import com.theeventsapi.mail.Mail;
import com.theeventsapi.repositorys.UsuarioRepository;

import com.theeventsapi.responses.Response;
import com.theeventsapi.services.UsuarioService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("${origem-permitida}")
// CASO QUEIRA LIBERAR QUALQUER ACESSO: @CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private Mail mail;
	 
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping()
	public ResponseEntity<Response<Usuario>> create(HttpServletRequest request, @RequestBody Usuario usuario,
			BindingResult result) {
		Response<Usuario> response = new Response<Usuario>();
		try {
			validateCreateUsuario(usuario, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setId(usuarioService.findCount() + 1L);
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setPerfil("ROLE_USUARIO");
			usuario.setAtivo(true);
			Usuario usuarioPersisted = (Usuario) usuarioService.createOrUpdate(usuario);
			response.setData(usuarioPersisted);
			mail.notificaUsuarioCriado(usuario.getEmail());
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("E-mail já registrado!");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateUsuario(Usuario usuario, BindingResult result) {
		if (usuario.getEmail() == null) {
			result.addError(new ObjectError("Usuario", "Email não informado!"));
			return;
		}
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> update(HttpServletRequest request, @RequestBody Usuario usuario,
			BindingResult result) {
		Response<Usuario> response = new Response<Usuario>();
		try {
			validateUpdate(usuario, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			Usuario userPersisted = (Usuario) usuarioService.createOrUpdate(usuario);
			response.setData(userPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateUpdate(Usuario user, BindingResult result) {
		if (user.getId() == null) {
			result.addError(new ObjectError("Usuario", "Id não informado!"));
			return;
		}
		if (user.getEmail() == null) {
			result.addError(new ObjectError("Usuario", "Email não informado!"));
			return;
		}
	}

	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> findById(@PathVariable("id") Long id) {
		Response<Usuario> response = new Response<Usuario>();
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(usuario);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		usuarioService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<Usuario>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<Usuario>> response = new Response<Page<Usuario>>();
		Page<Usuario> usuarios = usuarioService.findAllPage(page, count);
		response.setData(usuarios);
		return ResponseEntity.ok(response);
	}

	@GetMapping()
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/exportusuario")
	public ResponseEntity<byte[]> exportUsuario() throws JRException {
		List<Usuario> usuarios = usuarioService.findAll();
		Map<String, Object> parametros = new HashMap<>();
		InputStream x = getClass().getResourceAsStream("/reports/usuarioExport.jrxml");
		JasperReport is = JasperCompileManager.compileReport(x);
		JasperPrint print = JasperFillManager.fillReport(is, parametros, new JRBeanCollectionDataSource(usuarios));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(JasperExportManager.exportReportToPdf(print));
	}

}
