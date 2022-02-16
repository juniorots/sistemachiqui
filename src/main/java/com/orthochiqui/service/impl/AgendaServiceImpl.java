package com.orthochiqui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orthochiqui.exception.AgendaNotFoundException;
import com.orthochiqui.exception.ContatoNotFoundException;
import com.orthochiqui.model.Agenda;
import com.orthochiqui.repository.AgendaRepository;
import com.orthochiqui.service.AgendaService;
import com.orthochiqui.util.AgendaMapping;
import com.orthochiqui.util.IpirangaUtil;

/**
 * Service Agenda
 * @author Jose 
 */
@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	AgendaRepository agendaRepository;
	
	@Autowired
	AgendaMapping agendaMapping;
	
	@Override
	public Agenda getAgendaById(long id) throws AgendaNotFoundException {
		return agendaRepository.findById(id).orElseThrow(() -> 
			new AgendaNotFoundException("Agenda [ "+id+" ] nao localizada."));
	}

	@Override
	public Agenda saveAgenda(Agenda agenda) throws AgendaNotFoundException {
		return agendaRepository.save(agenda);
	}

	@Override
	public Agenda updateAgenda(long id, Agenda agenda) throws AgendaNotFoundException {
		Agenda a = agendaRepository.findById(id).orElseThrow(() -> 
			new AgendaNotFoundException("Agenda [ "+id+" ] nao encontrada."));
		Agenda aux = IpirangaUtil.memorizarIdsAgenda(a);
		a = agendaMapping.toAgenda(agenda);
		IpirangaUtil.devolverIdsAgenda(aux, a);
		return agendaRepository.save(a);
	}

	@Override
	public void deleteAgenda(long id) throws AgendaNotFoundException {
		agendaRepository.deleteById(id);
	}

}
