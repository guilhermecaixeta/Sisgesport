package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Estado;
import com.ifg.sistema.sisgesport.api.repositorios.EstadoRepositorio;
import com.ifg.sistema.sisgesport.api.services.EstadoService;
@Service
public class EstadoServiceImplementation implements EstadoService {
	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	@Cacheable("BuscarDadosCache")
	public Optional<List<Estado>> BuscarTodos() {
		log.info("Buscando todos os estados");
		return Optional.ofNullable(estadoRepositorio.findAll());
	}
	@Cacheable("BuscarDadosCache")
	public Optional<Estado> BuscarPorNomeOuUF(String nome, String UF){
		log.info("Buscando estado pelo nome: {1} ou UF: {2} ", nome, UF);
		return Optional.ofNullable(estadoRepositorio.findByNomeOrUf(nome, UF));
	}
	@Cacheable("BuscarDadosCache")
	public Optional<Estado> BuscarPorId(Long id){
		log.info("Buscando estado pelo id {} ", id);
		return Optional.ofNullable(estadoRepositorio.findOne(id));
	}
	public Estado Salvar(Estado estado) {
		log.info("Salvando o estado: ", estado.getNome());
		return estadoRepositorio.save(estado);
	}
	public void Deletar(Long id) {
		log.info("Deletando o estado com id: {}", id);
		estadoRepositorio.delete(id);
	}
}
