package com.orthochiqui.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.model.Cliente;
import com.orthochiqui.repository.ClienteRepository;
import com.orthochiqui.service.ClienteService;
import com.orthochiqui.util.ClienteMapping;
import com.orthochiqui.util.IpirangaUtil;

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
		List<Cliente> lista = new ArrayList<>();
		clienteRepository.findByNome(nome).forEach(lista::add);
		return lista;
	}

	@Override
	public Cliente getClienteByProntuario(String prontuario) throws ClienteNotFoundException {
		return clienteRepository.findByProntuario(prontuario).orElseThrow(() -> 
			new ClienteNotFoundException("Prontuario [ "+prontuario+" ] nao localizado."));
	}

	@Override
	public Cliente saveCliente(Cliente cliente) {
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
		Cliente aux = IpirangaUtil.memorizarIdsCliente(tmp);
		tmp = clienteMapping.toCliente(cliente);
		IpirangaUtil.devolverIdsCliente(aux, tmp);
		return clienteRepository.save(tmp);
	}

	@Override
	@Transactional
	public void deleteCliente(String prontuario) throws ClienteNotFoundException {
		clienteRepository.deleteByProntuario(prontuario);
	}

	@Override
	public List<Cliente> getClientes() {
		List<Cliente> lista = new ArrayList<>();
		clienteRepository.findAll().forEach(lista::add);
		return lista;
	}
	
	public List<String[]> montarArquivo() {
		List<String[]> list = new ArrayList<>();
		List<Cliente> listTmp = getClientes();
		list.add(new String[] {"PRONTUARIO", "NOME"});
		for (Cliente c : listTmp) 
			list.add(new String[] {c.getProntuario(), c.getNome()});
		
		return list;
	}
}
