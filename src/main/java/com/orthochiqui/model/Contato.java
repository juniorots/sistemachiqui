package com.orthochiqui.model;

import java.util.ArrayList;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name="contato")
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cro")
	private String cro;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dtnascimento")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dtNascimento;
	
	@Column(name="cpf_cnpj")
	private String cpfCnpj;
	
	@Column(name="receber_backup")
	private boolean receberBackUp;
			
	@OneToMany(cascade=CascadeType.ALL)
	private List<Telefone> telefones = new ArrayList<>();
	
	@OneToOne(cascade=CascadeType.ALL)
	private Banco banco = new Banco();
}
