package com.orthochiqui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orthochiqui.model.Contato;

/**
 * Interface JPA
 * @author Jose
 *
 */
public interface ContatoRepository extends JpaRepository<Contato, Long>{
	
	@Query("SELECT c FROM Contato c WHERE UPPER(c.nome) LIKE UPPER(concat('%', :nome, '%'))")
	List<Contato> findByNome(@Param("nome") String nome);
}
