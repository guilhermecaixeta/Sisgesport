package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ifg.sistema.sisgesport.api.entities.Servidor;

public interface ServidorService {
	/**
	 * Busca uma servidor pelo id
	 * @param id
	 * @return
	 */
	Optional<Servidor> BuscarPorId(Long id);
	/**
	 * Busca um servidor pelo nome
	 * @param nome
	 * @return
	 */
	Optional<Servidor> BuscarPorNome(String nome);
	/**
	 * Busca um servidor pelo email
	 * @param email
	 * @return
	 */
	Optional<Servidor> BuscarPorEmail(String email);
	/**
	 * Busca um servidor pela matricula siap
	 * @param matriculaSiap
	 * @return
	 */
	Optional<Servidor> BuscarPorMatriculaSiap(String matriculaSiap);
	/**
	 * Busca uma lista de servidores
	 * @return
	 */
	Optional<List<Servidor>> BuscarTodos();
	/**
	 * Busca uma lista de servidores pelo cargo id
	 * @param id_cargo
	 * @return
	 */
	Optional<List<Servidor>> BuscarPorCargoId(Long id_cargo);
	/**
	 * Busca uma lista paginada de servidores pelo cargo id
	 * @param id_cargo
	 * @param pageRequest
	 * @return
	 */
	Page<Servidor> BuscarPorCargoIdPaginavel(Long id_cargo, PageRequest pageRequest);
	/**
	 * Busca uma lista paginada de servidores pelo instituicao id
	 * @param id_instituicao
	 * @param pageRequest
	 * @return
	 */
	Page<Servidor> BuscarPorCargoInstituicaoIdPaginavel(Long id_instituicao, PageRequest pageRequest);
	/**
	 * Salva um novo servidor no banco de dados
	 * @param servidor
	 * @return
	 */
	Servidor Salvar(Servidor servidor);
	void Deletar(Long id);
}
