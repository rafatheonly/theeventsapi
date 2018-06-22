package com.theeventsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theeventsapi.entitys.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
}
