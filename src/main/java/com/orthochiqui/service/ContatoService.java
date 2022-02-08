package com.orthochiqui.service;

import java.util.List;

import com.orthochiqui.exception.ContatoNotFoundException;
import com.orthochiqui.model.Contato;

/**
 * Service custom contato
 * @author Jose
 *
 */
public interface ContatoService {
	public List<Contato> getContatoByNome(String nome) throws ContatoNotFoundException;	
	
	public Contato saveContato(Contato contato) throws ContatoNotFoundException;
	
	public Contato updateContato(Long id, Contato contato) throws ContatoNotFoundException;
	
	public void deleteContato(Long id) throws ContatoNotFoundException;
}
