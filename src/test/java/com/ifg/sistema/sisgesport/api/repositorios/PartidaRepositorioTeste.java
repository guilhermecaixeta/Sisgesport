package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.entities.Equipe;
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.Partida;
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.entities.Time;
import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class PartidaRepositorioTeste {

	@Autowired
	private TimeRepositorio tmR;
	@Autowired
	private EquipeRepositorio eR;
	@Autowired
	private EventoRepositorio evR;
	@Autowired
	private ServidorRepositorio svR;
	@Autowired
	private CargoRepositorio crR;
	@Autowired
	private ModalidadeRepositorio mR;
	@Autowired
	private TipoPontoRepositorio tpR;
	@Autowired
	private PartidaRepositorio pR;
	
	private static final Evento evento = CarregaEvento();
	private static final Cargo cargo = cargoServidor();
	private static final Servidor servidor = carregaServidor();
	private static final Equipe equipeCasa = carregarEquipeCasa();
	private static final Equipe equipeVisita = carregarEquipeVisita();
	private static final Modalidade modalidade = carregarModalidade();
	private static final Tipo_Ponto ponto = carregarTipoPonto();
	private static final Time timeCasa = carregarTimeCasa();
	private static final Time timeVisita = carregarTimeVisita();
	private static final Partida partida = CarregarPartida();
	
	@Before
	public void setUp() throws Exception{
		crR.save(cargo);
		svR.save(servidor);
		evR.save(evento);
		eR.save(equipeCasa);
		eR.save(equipeVisita);
		tpR.save(ponto);
		mR.save(modalidade);
		tmR.save(timeCasa);
		tmR.save(timeVisita);
		pR.save(partida);
	}
	
	@After
	public final void tearDown() {
		pR.deleteAll();
	}
	
	@Test
	public void carregarPartidaEventoPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Partida> pp = pR.findByEventoId(evento.getId(), page);
		assertNotNull(pp);
	}
	
	@Test
	public void carregarPartidaEvento() {
		List<Partida> pp = pR.findByEventoId(evento.getId());
		assertNotNull(pp);
	}
	
	@Test
	public void carregarPartidaPorTimePaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Partida> pp = pR.findByEventoId(timeCasa.getId(), page);
		assertNotNull(pp);
	}

	@Test
	public void carregarPartidaPorTime() {
		List<Partida> pp = pR.findByTimeCasaId(timeCasa.getId());
		assertNotNull(pp);
	}
	
	@Test
	public void carregarPartida() {
		List<Partida> pp = pR.findById(partida.getId());
		assertNotNull(pp);
	}
	
	
	private static Servidor carregaServidor() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme");
		serv.setData_nasc(new Date());
		serv.setLogin("usuario");
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula_siap("20122080010047");
		serv.setCargo(cargo);
		serv.setPerfil(PerfilSistema.ROLE_ADMIN);
		return serv;
	}
	
	private static Cargo cargoServidor() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor");
		return c;
	}
	
	private static Evento CarregaEvento() {
		Evento ev = new Evento();
		ev.setData_fim_inscricao(new Date());
		ev.setData_inicio_inscricao(new Date());
		ev.setData_fim(new Date());
		ev.setData_inicio(new Date());
		ev.setDescricao("Evento teste");
		ev.setNome("Evento de Teste");
		ev.setQnt_equipes(3);
		ev.setCriador(servidor);
		return ev;
	}
	
	private static Equipe carregarEquipeCasa() {
		Equipe eqp = new Equipe();
		eqp.setCor("Vermelho");
		eqp.setEvento(evento);
		eqp.setNome("Equipe um");
		return eqp; 
	}
	
	private static Equipe carregarEquipeVisita() {
		Equipe eqp = new Equipe();
		eqp.setCor("Azul");
		eqp.setEvento(evento);
		eqp.setNome("Equipe dois");
		return eqp; 
	}
	
	private static Modalidade carregarModalidade(){
		Modalidade mod = new Modalidade();
		List<Tipo_Ponto> lista = new ArrayList<Tipo_Ponto>();
		lista.add(ponto);
		mod.setDescricao("Esporte Coletivo de até 11 jogadores.");
		mod.setNome("Futebol");
		mod.setTipoPonto(lista);
		mod.setNum_max_jogador(21);
		mod.setNum_min_jogador(11);
		return mod;
	}
	
	private static Tipo_Ponto carregarTipoPonto() {
		Tipo_Ponto ponto = new Tipo_Ponto();
		ponto.setNome("gol");
		ponto.setValor(1);
		return ponto;
	}
	
	private static Time carregarTimeCasa() {
		Time t= new Time();
		t.setEquipe(equipeCasa);
		t.setModalidade(modalidade);

		return t;
	}
	
	private static Time carregarTimeVisita() {
		Time t= new Time();
		t.setEquipe(equipeVisita);
		t.setModalidade(modalidade);
		return t;
	}
	
	public static Partida CarregarPartida() {
		Partida p = new Partida();
		p.setDataPartida(new Date());
		p.setDuracaoPartida(90);
		p.setEvento(evento);
		p.setJuiz(servidor);
		p.setModalidade(modalidade);
		p.setTimeCasa(timeCasa);
		p.setTimeVisita(timeVisita);
		return p;
	}
}
