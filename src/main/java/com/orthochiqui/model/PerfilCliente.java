package com.orthochiqui.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="perfilCliente")
public class PerfilCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="nome")
	private String nome;
}
