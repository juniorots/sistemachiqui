package com.orthochiqui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.model.Cliente;
import com.orthochiqui.service.impl.ClienteServiceImpl;

/**
 * Router
 * @author Jose
 *
 */
@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	ClienteServiceImpl clienteService;
	
	@GetMapping("/clientes/{nome}")
	public ResponseEntity<List<Cliente>> getClienteByNome(@PathVariable("nome") String nome) {
		try {			
			return new ResponseEntity<List<Cliente>>(clienteService.getClienteByNome(nome), HttpStatus.OK);
		} catch (ClienteNotFoundException ce) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // :..-(
		}
	}
	
	@GetMapping("/clientes/prontuario/{prontuario}")
	public ResponseEntity<Cliente> getClienteByProntuario(@PathVariable("prontuario") String prontuario) {
		try {			
			return new ResponseEntity<Cliente>(clienteService.getClienteByProntuario(prontuario), HttpStatus.OK);
		} catch (ClienteNotFoundException ce) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // :..-(
		}
	}
	
	@PutMapping("/clientes/prontuario/{prontuario}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("prontuario") String prontuario, @RequestBody Cliente cliente) {
		try {
			return new ResponseEntity<>(clienteService.updateCliente(prontuario, cliente), HttpStatus.OK);
		} catch (ClienteNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // :..-(
		}
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		try {	
			return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // :..-(
		}
	}
	
}
