package com.orthochiqui.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.mapstruct.Mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Core Model
 * @author Jose
 *
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="prontuario")
	private String prontuario;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cpf")
	private String cpf;
		
	@Column(name="indicacao")
	private String indicacao;
	
	@Column(name="indicacaoResponsavel")
	private String indicacaoResponsavel;
	
	@OneToOne(cascade=CascadeType.ALL)
	private PerfilCliente perfilCliente = new PerfilCliente();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Telefone> telefones = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Orcamento> orcamentos = new ArrayList<>();	
}
