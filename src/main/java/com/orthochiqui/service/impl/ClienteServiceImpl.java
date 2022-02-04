package com.orthochiqui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.model.Cliente;
import com.orthochiqui.repository.ClienteRepository;
import com.orthochiqui.service.ClienteService;

/**
 * Service Client
 * @author Jose 
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> getClienteByNome(String nome) throws ClienteNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente getClienteByProntuario(String prontuario) throws ClienteNotFoundException {
		return clienteRepository.findByProntuario(prontuario).orElseThrow(() -> 
			new ClienteNotFoundException("Prontuario [ "+prontuario+" ] nao localizado."));
	}

	@Override
	public Cliente saveCliente(Cliente cliente) throws ClienteNotFoundException {
		return clienteRepository.save(cliente);		
	}
}
