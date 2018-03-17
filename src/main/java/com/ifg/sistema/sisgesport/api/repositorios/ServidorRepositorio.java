package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Servidor;;
@Transactional(readOnly=true)
public interface ServidorRepositorio extends JpaRepository<Servidor, Long> {

	Servidor findByMatriculasiap(String matriculasiap);
	
	List<Servidor> findByCargoId(Integer id);
	
	Page<Servidor> findByCargoInstituicaoId(Integer id, Pageable pageable);
}
