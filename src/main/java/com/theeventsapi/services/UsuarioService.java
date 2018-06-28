package com.theeventsapi.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.theeventsapi.entitys.Usuario;

@Component
public interface UsuarioService {

	Usuario findByEmail(String email);

	Usuario createOrUpdate(Usuario usuario);

	Usuario findById(Long id);

	void delete(Long id);

	// PASSA A pagina Q SE QUER E A quantidade DE REGISTROS
	Page<Usuario> findAllPage(int pagina, int quantidade);
	
	Long findCount();
}
