package com.orthochiqui.util;

import org.mapstruct.Mapper;

import com.orthochiqui.model.Cliente;

/**
 * Mapping structure workout
 * @author Jose
 *
 */
@Mapper(componentModel="spring")
public interface ClienteMapping {
	Cliente toCliente(Cliente cliente);	
}
