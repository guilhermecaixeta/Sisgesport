package com.ifg.sistema.sisgesport.api.dto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ifg.sistema.sisgesport.api.dto.commom_entities.EntidadeComumDTO;

public class EventoDTO extends EntidadeComumDTO {
	private int qntEquipes;
	private String codigoEvento;
	private String nome;
	private String descricao;
	private Date dataInicioInscricao;
	private Date dataFimInscricao;
	private Date dataCriacao;
	private Date dataInicio;
	private Date dataFim;
	private ServidorDTO criador;
	private List<EventoModalidadeDTO> eventoModalidade = new ArrayList<EventoModalidadeDTO>();

	public EventoDTO() {
	}

	public int getQntEquipes() {
		return qntEquipes;
	}

	public void setQntEquipes(int qntEquipes) {
		this.qntEquipes = qntEquipes;
	}

	public String getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(String codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicioInscricao() {
		return dataInicioInscricao;
	}

	public void setDataInicioInscricao(Date dataInicioInscricao) {
		this.dataInicioInscricao = dataInicioInscricao;
	}

	public Date getDataFimInscricao() {
		return dataFimInscricao;
	}

	public void setDataFimInscricao(Date dataFimInscricao) {
		this.dataFimInscricao = dataFimInscricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public ServidorDTO getCriador() {
		return criador;
	}

	public void setCriador(ServidorDTO criador) {
		this.criador = criador;
	}
	
	public List<EventoModalidadeDTO> getEventoModalidade() {
		return eventoModalidade;
	}

	public void setEventoModalidade(List<EventoModalidadeDTO> eventoModalidade) {
		this.eventoModalidade = eventoModalidade;
	}

}
