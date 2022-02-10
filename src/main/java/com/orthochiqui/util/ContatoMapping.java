package com.orthochiqui.util;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.orthochiqui.model.Cliente;
import com.orthochiqui.model.Contato;

/**
 * Mapping structure workout
 * @author Jose
 *
 */
@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContatoMapping {
	Contato toContato(Contato contato);	
}
