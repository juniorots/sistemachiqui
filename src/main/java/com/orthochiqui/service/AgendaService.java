package com.orthochiqui.service;

import com.orthochiqui.exception.AgendaNotFoundException;
import com.orthochiqui.model.Agenda;

/**
 * Service custom agenda
 * @author Jose
 */
public interface AgendaService {
	public Agenda getAgendaById(long id) throws AgendaNotFoundException;
	
	public Agenda saveAgenda(Agenda agenda) throws AgendaNotFoundException;
	
	public Agenda updateAgenda(long id, Agenda agenda) throws AgendaNotFoundException;
	
	public void deleteAgenda(long id) throws AgendaNotFoundException;
}
