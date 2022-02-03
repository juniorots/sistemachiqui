package com.orthochiqui.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orthochiqui.model.Cliente;

/**
 * Interface JPA
 * @author Jose
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Optional<Cliente> findByProntuario(String prontuario);

	List<Cliente> findByNome(String nome);
}
