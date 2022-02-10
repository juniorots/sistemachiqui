package com.orthochiqui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orthochiqui.exception.UsuarioNotFoundException;
import com.orthochiqui.model.Usuario;
import com.orthochiqui.repository.UsuarioRepository;
import com.orthochiqui.service.UsuarioService;
import com.orthochiqui.util.IpirangaUtil;
import com.orthochiqui.util.UsuarioMapping;

/**
 * Service Client
 * @author Jose 
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioMapping usuarioMapping;
	
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
		Usuario tmp = usuarioRepository.findById(id).orElseThrow(() -> 
		new UsuarioNotFoundException("Cliente [ "+id+" ] nao encontrado."));
		Usuario aux = IpirangaUtil.memorizarIdsUsuario(tmp);
		tmp = usuarioMapping.toUsuario(usuario);
		IpirangaUtil.devolverIdsUsuario(aux, tmp);
		return usuarioRepository.save(tmp);
	}

	@Override
	public void deleteUsuario(long id) throws UsuarioNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario getUsuarioById(long id) throws UsuarioNotFoundException {
		return usuarioRepository.findById(id).orElseThrow(() -> 
			new UsuarioNotFoundException("Usuario [ "+id+" ] nao localizado."));
	}

}
