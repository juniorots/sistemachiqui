package com.orthochiqui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.model.Cliente;
import com.orthochiqui.repository.ClienteRepository;
import com.orthochiqui.service.ClienteService;
import com.orthochiqui.util.ClienteIpiranga;
import com.orthochiqui.util.ClienteMapping;

/**
 * Service Client
 * @author Jose 
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteMapping clienteMapping;
	
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

	@Override
	public Cliente updateCliente(String prontuario, Cliente cliente) throws ClienteNotFoundException {
		Cliente tmp = clienteRepository.findByProntuario(prontuario).orElseThrow(() -> 
				new ClienteNotFoundException("Cliente [ "+prontuario+" ] nao encontrado."));
		/*
		 * NOTA: Devido a ausencia de um attributo do tipo override=false para um target
		 * especifico na manipulacao do Mapping, fora necessario
		 * contornar o problema com a espeficacao dos metodos estaticos
		 * utilizados abaixo para os campos especificos de ID
		 */
		Cliente aux = ClienteIpiranga.memorizarIds(tmp);
		tmp = clienteMapping.toCliente(cliente);
		ClienteIpiranga.devolverIds(aux, tmp);
		return clienteRepository.save(tmp);
	}
}
