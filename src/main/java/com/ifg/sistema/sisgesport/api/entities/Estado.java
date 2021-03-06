package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {

	private static final long serialVersionUID = 6499180307554549420L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false, unique = true, length = 20)
	@NotNull(message = "O campo estado não pode ser nulo.")
	@NotBlank(message = "O campo estado não pode ser em branco.")
	@Length(max = 20, message = "O campo possui o limite máximo de {max} caracteres.")
	private String nome;

	@NotNull(message = "O campo UF não pode ser nulo.")
	@NotBlank(message = "O campo UF não pode ser em branco.")
	@Length(max = 2, message = "O UF possui o limite máximo de {max} caracteres.")
	@Column(name = "uf", nullable = false, unique = true, length = 2)
	private String uf;

	public Estado() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
