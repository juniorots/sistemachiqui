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
public interface ClienteMapping {
	
//	@Mappings({
//		@Mapping(target = "id", ignore = true),
//		@Mapping(target = "perfilCliente.id", ignore = true),
//		@Mapping(target = "telefones.telefone.id", ignore = true),
//		@Mapping(target = "orcamentos.orcamento.id", ignore = true),
//		@Mapping(target = "orcamentos.procedimentos.procedimento.id", ignore = true)
//	})
	Cliente toCliente(Cliente cliente);	
}
