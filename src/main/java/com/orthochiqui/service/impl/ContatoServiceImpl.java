package com.orthochiqui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orthochiqui.exception.ContatoNotFoundException;
import com.orthochiqui.model.Cliente;
import com.orthochiqui.model.Contato;
import com.orthochiqui.repository.ContatoRepository;
import com.orthochiqui.service.ContatoService;
import com.orthochiqui.util.ContatoMapping;
import com.orthochiqui.util.IpirangaUtil;

@Service
public class ContatoServiceImpl implements ContatoService {

	@Autowired
	ContatoRepository contatoRepository;
	
	@Autowired
	ContatoMapping contatoMapping;
	
	@Override
	public List<Contato> getContatoByNome(String nome) throws ContatoNotFoundException {
		List<Contato> lista = new ArrayList<>();
		contatoRepository.findByNomeLike("%"+nome+"%").forEach(lista::add);
		return lista;
	}

	@Override
	public Contato saveContato(Contato contato) throws ContatoNotFoundException {
		return contatoRepository.save(contato); 
	}

	@Override
	public Contato updateContato(Long id, Contato contato) throws ContatoNotFoundException {
		Contato c = contatoRepository.findById(id).orElseThrow(() -> 
						new ContatoNotFoundException("Contato [ "+id+" ] nao encontrado."));
		Contato aux = IpirangaUtil.memorizarIdsContato(c);
		c = contatoMapping.toContato(contato);
		IpirangaUtil.devolverIdsContato(aux, c);
		return contatoRepository.save(c);
	}

	@Override
	public void deleteContato(Long id) throws ContatoNotFoundException {
		contatoRepository.deleteById(id);
	}

	@Override
	public Contato getContatoById(Long id) throws ContatoNotFoundException {
		return contatoRepository.findById(id).orElseThrow(() -> 
			new ContatoNotFoundException("Contato [ "+id+" ] nao localizado."));
	}
	
}
