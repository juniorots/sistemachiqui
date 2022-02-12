package com.orthochiqui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orthochiqui.exception.ContatoNotFoundException;
import com.orthochiqui.exception.UsuarioNotFoundException;
import com.orthochiqui.model.Contato;
import com.orthochiqui.model.Usuario;
import com.orthochiqui.service.impl.UsuarioServiceImpl;

/**
 * Router
 * @author Jose
 *
 */
@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> createCliente(@RequestBody Usuario usuario) {
		try {	
			return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // :..-(
		}
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
		try {
			return new ResponseEntity<>(usuarioService.updateUsuario(id, usuario), HttpStatus.OK);
		} catch (UsuarioNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // :..-(
		}
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
		try {			
			return new ResponseEntity<Usuario>(usuarioService.getUsuarioById(id), HttpStatus.OK);
		} catch (UsuarioNotFoundException ce) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // :..-(
		}
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") Long id) {
		try {
			usuarioService.deleteUsuario(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // :..-(
		}
	}
	
	@GetMapping("/usuarios/login/{login}")
	public ResponseEntity<List<Usuario>> getContatos(@PathVariable("login") String login) {
		try {			
			return new ResponseEntity<List<Usuario>>(usuarioService.getUsuarioByLogin(login), HttpStatus.OK);
		} catch (UsuarioNotFoundException ce) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // :..-(
		}
	}
}
