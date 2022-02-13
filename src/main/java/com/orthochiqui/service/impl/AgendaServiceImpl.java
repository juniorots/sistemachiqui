package com.orthochiqui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orthochiqui.exception.AgendaNotFoundException;
import com.orthochiqui.model.Agenda;
import com.orthochiqui.repository.AgendaRepository;
import com.orthochiqui.service.AgendaService;

/**
 * Service Agenda
 * @author Jose 
 */
@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	AgendaRepository agendaRepository;
	
	@Override
	public Agenda getAgendaById(long id) throws AgendaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agenda saveAgenda(Agenda agenda) throws AgendaNotFoundException {
		return agendaRepository.save(agenda);
	}

	@Override
	public Agenda updateAgenda(long id, Agenda agenda) throws AgendaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAgenda(long id) throws AgendaNotFoundException {
		// TODO Auto-generated method stub

	}

}
