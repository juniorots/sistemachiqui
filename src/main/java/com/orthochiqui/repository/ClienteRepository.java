package com.orthochiqui.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orthochiqui.model.Cliente;
import com.orthochiqui.model.Usuario;

/**
 * Interface JPA
 * @author Jose
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Optional<Cliente> findByProntuario(String prontuario);

	@Query("SELECT c FROM Cliente c WHERE UPPER(c.nome) LIKE UPPER(concat('%', :nome, '%'))")
	List<Cliente> findByNome(@Param("nome") String nome);
	
	void deleteByProntuario(String prontuario);
}
