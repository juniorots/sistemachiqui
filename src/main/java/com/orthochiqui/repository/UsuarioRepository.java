package com.orthochiqui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orthochiqui.model.Usuario;

/**
 * Interface JPA
 * @author Jose
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u WHERE UPPER(u.login) LIKE UPPER(concat('%', :login, '%'))")
	List<Usuario> findByLogin(@Param("login") String login);
}
