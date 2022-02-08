package com.orthochiqui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orthochiqui.exception.ClienteNotFoundException;
import com.orthochiqui.exception.ContatoNotFoundException;
import com.orthochiqui.model.Contato;
import com.orthochiqui.repository.ContatoRepository;
import com.orthochiqui.service.ContatoService;

@Service
public class ContatoServiceImpl implements ContatoService {

	@Autowired
	ContatoRepository contatoRepository;
	
	@Override
	public List<Contato> getContatoByNome(String nome) throws ContatoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contato saveContato(Contato contato) throws ContatoNotFoundException {
		return contatoRepository.save(contato); 
	}

	@Override
	public Contato updateContato(Long id, Contato contato) throws ContatoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteContato(Long id) throws ContatoNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contato getContatoById(Long id) throws ContatoNotFoundException {
		return contatoRepository.findById(id).orElseThrow(() -> 
		new ContatoNotFoundException("Contato [ "+id+" ] nao localizado."));
	}
	
}
