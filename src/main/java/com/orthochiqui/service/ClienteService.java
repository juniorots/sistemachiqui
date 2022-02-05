package com.orthochiqui.service;

import java.util.List;

import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.model.Cliente;

/**
 * Service custom client
 * @author Jose
 *
 */
public interface ClienteService {
	public List<Cliente> getClienteByNome(String nome) throws ClienteNotFoundException;
	
	public Cliente getClienteByProntuario(String prontuario) throws ClienteNotFoundException;
	
	public Cliente saveCliente(Cliente cliente) throws ClienteNotFoundException;
	
	public Cliente updateCliente(String prontuario, Cliente cliente) throws ClienteNotFoundException;
}

