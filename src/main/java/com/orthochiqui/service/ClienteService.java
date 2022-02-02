package com.orthochiqui.service;

import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.model.Cliente;

/**
 * Service custom client
 * @author Jose
 *
 */
public interface ClienteService {
	public Cliente getClienteByNome(String nome) throws ClienteNotFoundException;
	
	public Cliente getClienteByProntuario(String prontuario) throws ClienteNotFoundException;
}
