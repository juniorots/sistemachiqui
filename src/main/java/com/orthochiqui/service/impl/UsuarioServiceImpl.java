package com.orthochiqui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orthochiqui.exception.UsuarioNotFoundException;
import com.orthochiqui.model.Usuario;
import com.orthochiqui.repository.UsuarioRepository;
import com.orthochiqui.service.UsuarioService;

/**
 * Service Client
 * @author Jose 
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> getUsuarioByNome(String nome) throws UsuarioNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario saveUsuario(Usuario usuario) throws UsuarioNotFoundException {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario updateUsuario(long id, Usuario usuario) throws UsuarioNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUsuario(long id) throws UsuarioNotFoundException {
		// TODO Auto-generated method stub

	}

}
