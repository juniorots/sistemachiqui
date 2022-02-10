package com.orthochiqui.util;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.orthochiqui.model.Usuario;

/**
 * Mapping structure workout
 * @author Jose
 *
 */
@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapping {
	
	Usuario toUsuario(Usuario usuarioO);	
}
