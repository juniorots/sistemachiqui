package com.orthochiqui.util;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.orthochiqui.model.Agenda;

/**
 * Mapping structure workout
 * @author Jose
 */
@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgendaMapping {
	Agenda toAgenda(Agenda agenda);	
}
