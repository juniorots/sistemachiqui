package com.orthochiqui.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Model core business
 * @author Jose
 *
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="agenda")
public class Agenda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="dtAgenda")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date dtAgenda;
	
	@Column(name="status")
	private String status;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Cliente cliente;
}
