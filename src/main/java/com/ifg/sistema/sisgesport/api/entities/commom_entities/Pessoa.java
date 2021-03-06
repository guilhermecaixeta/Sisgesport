package com.ifg.sistema.sisgesport.api.entities.commom_entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.ifg.sistema.sisgesport.api.entities.Instituicao;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public class Pessoa extends EntidadeComum implements Serializable {

	private static final long serialVersionUID = 2848996214116693556L;

	@Column(name = "nome", nullable = false, length = 255)
	@NotNull(message = "O campo nome não pode ser nulo.")
	@NotBlank(message = "O campo nome não pode ser em branco.")
	@Length(max = 255, message = "O campo possui o limite máximo de {max} caracteres.")
	protected String nome;

	@Column(name = "sexo", nullable = false, length = 1)
	@NotNull(message = "O campo sexo não pode ser nulo.")
	protected Character sexo;

	@Column(name = "matricula", nullable = false, unique = true, length = 20)
	@NotNull(message = "O campo matricula não pode ser nulo.")
	@NotBlank(message = "O campo matricula não pode ser em branco.")
	protected String matricula;
	
	@Column(name = "senha", nullable = false, length = 60)
	@NotNull(message = "O campo senha não pode ser nulo.")
	@NotBlank(message = "O campo senha não pode ser em branco.")
	@Length(max = 60, message = "A senha possui o limite máximo de {max} caracteres.")
	protected String senha;

	@Column(name = "email", length = 255, unique = true)
	@Length(max = 255, message = "O email possui o limite máximo de {max} caracteres.")
	@Email(message = "Email não é válido.")
	protected String email;

	@Column(name = "data_nasc", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	protected PerfilSistema perfil;

	@ManyToOne
	@JoinColumn(name = "instituicao", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_pessoa_instituicao"))
	private Instituicao instituicao;

	public Pessoa() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public PerfilSistema getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilSistema perfil) {
		this.perfil = perfil;
	}

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

	@PrePersist
	public void PrePersist() {
		senha = PasswordUtils.GerarBCrypt(this.senha);
	}
}
