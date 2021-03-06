package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ifg.sistema.sisgesport.api.entities.Modalidade;

public interface ModalidadeService {
	/**
	 * Busca a modalidade pelo id
	 * @param id
	 * @return Optional<Modalidade>
	 */
	Optional<Modalidade> BuscarPorId(Long id);
	/**
	 * Busca a modalidade pelo nome
	 * @param nome
	 * @return Optional<Modalidade>
	 */
	Optional<Modalidade> BuscarPorNome(String nome);
	/**
	 * Retorna todas as modalidades paginadas
	 * @return
	 */
	Page<Modalidade> BuscarTodosPaginavel(PageRequest pageRequest);
	/**
	 * Retorna todas as modalidades
	 * @return
	 */
    Optional<List<Modalidade>> BuscarTodos();
	/**
	 * Salva uma nova modalidade no banco de dados
	 * @param modalidade
	 * @return Modalidade
	 */
	Modalidade Salvar(Modalidade modalidade);
	void Deletar(Long id);
}
