package com.theeventsapi.services.implementacoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.theeventsapi.entitys.Usuario;
import com.theeventsapi.repositorys.UsuarioRepository;
import com.theeventsapi.services.UsuarioService;

@Component
public class UsuarioServiceImplementa implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario findByEmail(String email) {
		return this.usuarioRepository.findByEmail(email);
	}

	public Usuario createOrUpdate(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	public Usuario findById(Long id) {
		return this.usuarioRepository.findById(id).get();
	}

	public void delete(Long id) {
		this.usuarioRepository.findById(id).get();
	}

	public Page<Usuario> findAllPage(int pagina, int quantidade) {
		PageRequest pageRequest = PageRequest.of(pagina, quantidade);
		return this.usuarioRepository.findAll(pageRequest);
	}
	
	public Long findCount() {		
		return this.usuarioRepository.findCount();
	}
	
	public List<Usuario> findAll() {	
		return this.usuarioRepository.findAll();
	}
}
