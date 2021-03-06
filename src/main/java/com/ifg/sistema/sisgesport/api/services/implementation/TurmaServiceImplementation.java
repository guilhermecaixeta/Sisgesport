package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Turma;
import com.ifg.sistema.sisgesport.api.repositorios.TurmaRepositorio;
import com.ifg.sistema.sisgesport.api.services.TurmaService;
@Service
public class TurmaServiceImplementation implements TurmaService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private TurmaRepositorio turmaRepositorio;
	@Cacheable("BuscarDadosCacheTurma")
	public Optional<Turma> BuscarPorId(Long id) {
		log.info("Buscando servidor pelo id {} ", id);
		return Optional.ofNullable(turmaRepositorio.findOne(id));
	}
	@Cacheable("BuscarDadosCacheTurma")
	public Optional<Turma> BuscarPorNome(String nome) {
		log.info("Buscando Turma pelo nome {} ", nome);
		return Optional.ofNullable(turmaRepositorio.findByNomeEquals(nome));
	}
	@Cacheable("BuscarDadosCacheTurma")
	public Optional<List<Turma>> BuscarPorCursoId(Long id_curso) {
		log.info("Buscando Turma pelo id curso {} ", id_curso);
		return Optional.ofNullable(turmaRepositorio.findByCursoId(id_curso));
	}

	public Page<Turma> BuscarPorCursoIdPaginavel(Long id_curso, PageRequest pageRequest) {
		log.info("Buscando Turma pelo id {} ", id_curso);
		return turmaRepositorio.findByCursoId(id_curso, pageRequest);
	}

	public Page<Turma> BuscarTodosPaginavel( PageRequest pageRequest) {
		log.info("Buscando todas as turmas paginadas");
		return turmaRepositorio.findAll(pageRequest);
	}
	@CachePut("BuscarDadosCacheTurma")
	public Turma Salvar(Turma turma) {
		log.info("Salvando uma nova Turma no banco de dados {} ", turma);
		return turmaRepositorio.save(turma);
	}
	public void Deletar(Long id) {
		log.info("Deletando a turma  ponto com id: {}", id);
		turmaRepositorio.delete(id);
	}
}
