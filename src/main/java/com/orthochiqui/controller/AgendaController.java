package com.orthochiqui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orthochiqui.model.Agenda;
import com.orthochiqui.service.impl.AgendaServiceImpl;

/**
 * Router
 * @author Jose
 *
 */
@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AgendaController {
	
	@Autowired
	AgendaServiceImpl agendaService;
	
	@PostMapping("/agendas")
	public ResponseEntity<Agenda> createAgenda(@RequestBody Agenda agenda) {
		try {	
			return new ResponseEntity<>(agendaService.saveAgenda(agenda), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // :..-(
		}
	}
}
