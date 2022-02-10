package com.orthochiqui.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.orthochiqui.model.Cliente;
import com.orthochiqui.model.Contato;
import com.orthochiqui.model.Orcamento;
import com.orthochiqui.model.PermissaoUsuario;
import com.orthochiqui.model.Procedimento;
import com.orthochiqui.model.Telefone;
import com.orthochiqui.model.Usuario;

/**
 * All markee Cliente
 * @author Jose
 *
 */
public class IpirangaUtil {
	public static Cliente memorizarIdsCliente(Cliente origem) {
		Cliente retorno = new Cliente();
		retorno.setId(origem.getId());
		retorno.getPerfilCliente().setId(origem.getPerfilCliente().getId());
		retorno.setTelefones(origem.getTelefones());
		retorno.setOrcamentos(origem.getOrcamentos());
		return retorno;
	}
	
	public static void devolverIdsCliente(Cliente origem, Cliente atualizado) {
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
	
	public static Contato memorizarIdsContato(Contato origem) {
		Contato retorno = new Contato();
		retorno.setId(origem.getId());
		retorno.getBanco().setId(origem.getBanco().getId());
		retorno.setTelefones(origem.getTelefones());
		return retorno;
	}
	
	public static void devolverIdsContato(Contato origem, Contato atualizado) {
		atualizado.setId(origem.getId());
		atualizado.getBanco().setId(origem.getBanco().getId());
		for (Telefone t : atualizado.getTelefones()) 			
			for (Telefone t2 : origem.getTelefones())
				if (t.getNumero().equals(t2.getNumero())) t.setId(t2.getId());
	}
	
	public static String gerarHash(String texto) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new StringBuilder().toString();
		}
		return new BigInteger(1, md.digest(texto.getBytes())).toString();
	}
	
	public static Usuario memorizarIdsUsuario(Usuario origem) {
		Usuario retorno = new Usuario();
		retorno.setId(origem.getId());
		retorno.setPermissaoUsuario(origem.getPermissaoUsuario());
		return retorno;
	}
	
	public static void devolverIdsUsuario(Usuario origem, Usuario atualizado) {
		atualizado.setId(origem.getId());
		for (PermissaoUsuario p : atualizado.getPermissaoUsuario()) 			
			for (PermissaoUsuario p2 : origem.getPermissaoUsuario())
				if (p.getPermissao().equals(p2.getPermissao())) p.setId(p2.getId());
	}
}
