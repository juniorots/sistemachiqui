package com.orthochiqui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.model.Cliente;
import com.orthochiqui.service.ClienteService;

/**
 * Router
 * @author Jose
 *
 */
@CrossOrigin(origins="http://localhost:8082")
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/clientes/{nome}")
	public ResponseEntity<Cliente> getClienteByNome(@PathVariable("nome") String nome) {
		try {			
			return new ResponseEntity<>(clienteService.getClienteByNome(nome), HttpStatus.OK);
		} catch (ClienteNotFoundException ce) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // :..-(
		}
	}
}
