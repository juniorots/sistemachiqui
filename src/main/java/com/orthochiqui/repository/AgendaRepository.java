package com.orthochiqui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orthochiqui.model.Agenda;

/**
 * Interface JPA
 * @author Jose
 */
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
