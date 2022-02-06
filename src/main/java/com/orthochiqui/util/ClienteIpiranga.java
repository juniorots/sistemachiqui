package com.orthochiqui.util;

import com.orthochiqui.model.Cliente;
import com.orthochiqui.model.Orcamento;
import com.orthochiqui.model.Procedimento;
import com.orthochiqui.model.Telefone;

/**
 * All markee Cliente
 * @author Jose
 *
 */
public class ClienteIpiranga {
	public static Cliente memorizarIds(Cliente origem) {
		Cliente retorno = new Cliente();
		retorno.setId(origem.getId());
		retorno.getPerfilCliente().setId(origem.getPerfilCliente().getId());
		retorno.setTelefones(origem.getTelefones());
		retorno.setOrcamentos(origem.getOrcamentos());
		return retorno;
	}
	
	public static void devolverIds(Cliente origem, Cliente atualizado) {
		atualizado.setId(origem.getId());
		atualizado.getPerfilCliente().setId(origem.getPerfilCliente().getId());
		for (Telefone t : atualizado.getTelefones()) 			
			for (Telefone t2 : origem.getTelefones())
				if (t.getNumero().equals(t2.getNumero())) t.setId(t2.getId());
		
		for (Orcamento o : atualizado.getOrcamentos())
			for (Orcamento o2 : origem.getOrcamentos())
				if (o.getDtOrcamento().equals(o2.getDtOrcamento())) {
					o.setId(o2.getId());
					for (Procedimento p : o.getProcedimentos())
						for (Procedimento p2 : o2.getProcedimentos())
							if (p.getNome().equals(p2.getNome()) 
									&& p.getNrDente().equals(p2.getNrDente())
									&& p.getVrProcedimento().equals(p2.getVrProcedimento())) p.setId(p2.getId());
				}		
	}
}
