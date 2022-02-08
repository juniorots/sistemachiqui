package com.orthochiqui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orthochiqui.model.Contato;

/**
 * Interface JPA
 * @author Jose
 *
 */
public interface ContatoRepository extends JpaRepository<Contato, Long>{
	List<Contato> findByNomeLike(String nome);
}
