package com.orthochiqui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orthochiqui.model.Usuario;

/**
 * Interface JPA
 * @author Jose
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByLoginLike(String login);
}
