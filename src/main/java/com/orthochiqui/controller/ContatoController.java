package com.orthochiqui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orthochiqui.model.Contato;
import com.orthochiqui.service.impl.ContatoServiceImpl;

/**
 * Router
 * @author Jose
 *
 */
@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ContatoController {
	
	@Autowired
	ContatoServiceImpl contatoService;
	
	@PostMapping("/contatos")
	public ResponseEntity<Contato> createCliente(@RequestBody Contato contato) {
		try {	
			return new ResponseEntity<>(contatoService.saveContato(contato), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // :..-(
		}
	}
	
	
}
