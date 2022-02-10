package com.orthochiqui.service;

import java.util.List;

import com.orthochiqui.exception.UsuarioNotFoundException;
import com.orthochiqui.model.Usuario;

/**
 * Service custom usuario
 * @author Jose
 *
 */
public interface UsuarioService {
	public List<Usuario> getUsuarioByNome(String nome) throws UsuarioNotFoundException;
	
	public Usuario saveUsuario(Usuario usuario) throws UsuarioNotFoundException;
	
	public Usuario updateUsuario(long id, Usuario usuario) throws UsuarioNotFoundException;
	
	public void deleteUsuario(long id) throws UsuarioNotFoundException;
}
