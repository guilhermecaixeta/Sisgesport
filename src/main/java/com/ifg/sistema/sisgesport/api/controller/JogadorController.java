package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.JogadorDTO;
import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.entities.PartidaPenalidade;
import com.ifg.sistema.sisgesport.api.entities.PartidaPonto;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.JogadorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/jogador")
public class JogadorController extends baseController<JogadorDTO, Jogador, JogadorService> {
	{
		listaExcecao.add("id");
		listaExcecao.add("serialVersionUID");
		mappingDTOToEntity = new Extension<>(JogadorDTO.class, Jogador.class);
		mappingEntityToDTO = new Extension<>(Jogador.class, JogadorDTO.class);
	}

	@GetMapping(value = "/BuscarPorEquipeIdPaginavel/{id_jogador}")
	public ResponseEntity<Response<Page<JogadorDTO>>> BuscarJogadorPorIdEventoPaginavel(
            @PathVariable("id_jogador") Long id_jogador,
            PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorEquipeIdPaginavel(id_jogador, pageRequest));
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarJogadorPorIdEvento/{id_jogador}")
	public ResponseEntity<Response<List<JogadorDTO>>> BuscarJogadorPorIdEvento(
			@PathVariable("id_jogador") Long id_jogador) {
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarPorEquipeId(id_jogador).get(), false);
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<JogadorDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Instituição com o id: {}", id);
		entityOptional = entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(entityOptional.get()));
		return ResponseEntity.ok(response);
	}

    /**
     * Controller para adicionar um novo jogador
     * @param jogadorDTO
     * @param result
     * @return
     * @throws NoSuchAlgorithmException
     */
	@PostMapping
	public ResponseEntity<Response<JogadorDTO>> CadastrarJogador(@Valid @RequestBody JogadorDTO jogadorDTO, BindingResult result)
            throws NoSuchAlgorithmException {
		log.info("Cadastrando a jogador: {}", jogadorDTO.toString());
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova jogador: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(jogadorDTO);
		List<PartidaPenalidade> lista = entity.getPartidaPenalidade();
		entity.setPartidaPenalidade(new ArrayList<PartidaPenalidade>());
		if (!lista.isEmpty())
			lista.forEach(ppenalidade -> entity.AdicionarPartidaPenalidade(ppenalidade));

		List<PartidaPonto> listaPartidaPonto = entity.getPartidaPonto();
		entity.setPartidaPonto(new ArrayList<PartidaPonto>());
		if (!listaPartidaPonto.isEmpty())
			listaPartidaPonto.forEach(pponto -> entity.AdicionarPartidaPonto(pponto));
		
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

    /**
     * Controller para atualização dos dados do jogador
     * @param id
     * @param jogadorDTO
     * @param result
     * @return
     * @throws Exception
     */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<JogadorDTO>> AtualizarJogador(@PathVariable("id") Long id,
			@Valid @RequestBody JogadorDTO jogadorDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Jogador: {}", jogadorDTO);
		entityOptional = this.entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(jogadorDTO, entityOptional.get(), listaExcecao);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(this.entityService.Salvar(entity)));
		return ResponseEntity.ok(response);
	}

    /**
     * Controller para a deleção do jogador
     * @param id
     * @return
     */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> DeletarJogador(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		if (!this.entityService.BuscarPorId(id).isPresent()) {
			log.info("Erro ao remover dados ligados ao id: {}", id);
			response.getErrors().add("Erro ao remover dado. Nenhum registro encontrado para o id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.entityService.Deletar(id);
		return ResponseEntity.ok(new Response<String>());
	}
}