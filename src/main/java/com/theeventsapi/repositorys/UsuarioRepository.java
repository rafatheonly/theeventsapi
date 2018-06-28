package com.theeventsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.theeventsapi.entitys.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
	
	@Query("select count(u.id) from Usuario u")
	    Long findCount();
}
