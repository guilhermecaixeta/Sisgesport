package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ifg.sistema.sisgesport.api.entities.*;
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

import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class TimeRepositorioTeste {
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
	private EventoModalidadeRepositorio EvMR;
	@Autowired
	private TipoPontoRepositorio tpR;

	private static final Evento evento = carregarEvento();
	private static final Cargo cargo = cargoServidor();
	private static final Servidor servidor = carregarServidor();
	private static final Equipe equipe = carregarEquipe();
	private static final Modalidade modalidade = carregarModalidade();
	private static final TipoPonto ponto = carregarTipoPonto();
	private static final Time time = carregarTime();
	private static final EventoModalidade EVENTO_MODALIDADE = carregarEventoModalidade();
	@Before
	public void setUp() throws Exception{
		crR.save(cargo);
		svR.save(servidor);
		evR.save(evento);
		eR.save(equipe);
		tpR.save(ponto);
		mR.save(modalidade);
		EvMR.save(EVENTO_MODALIDADE);
		tmR.save(time);
	}
	
	@After
	public final void tearDown() {
		tmR.deleteAll();
	}
	
	@Test
	public void testBuscarPorCodigoEquipe() {
		Time t = this.tmR.findByEquipeCodigoEquipe(equipe.getCodigoEquipe());
		
		assertNotNull(t);
	}
	
	@Test
	public void testBuscarporEquipeId() {
		List<Time> eqp = this.tmR.findByEquipeId(equipe.getId());
		
		assertNotNull(eqp);
	}
	
	@Test
	public void testBuscarporEquipeIdPaginavel() {
		PageRequest page = new PageRequest(0, 10);
		Page<Time> listatime = this.tmR.findByEquipeId(equipe.getId(), page);
		
		assertEquals(1, listatime.getTotalElements());
	}
	
	private static Servidor carregarServidor() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme");
		serv.setDataNascimento(new Date());
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula("20122080010001");
		serv.setCargo(cargo);
		serv.setPerfil(PerfilSistema.ROLE_ADMIN);
		return serv;
	}
	
	private static Cargo cargoServidor() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor Mestre em Tecnologia");
		return c;
	}
	
	private static Evento carregarEvento() {
		Evento ev = new Evento();
		ev.setDataFim(new Date());
		ev.setDataInicio(new Date());
		ev.setDataFimInscricao(new Date());
		ev.setDataInicioInscricao(new Date());
		ev.setDescricao("Evento teste");
		ev.setNome("Evento de Teste");
		ev.setQntEquipes(3);
		ev.setCriador(servidor);
		return ev;
	}
	
	private static Equipe carregarEquipe() {
		Equipe eqp = new Equipe();
		eqp.setCor("AEIOU");
		eqp.setEvento(evento);
		eqp.setNome("Equipe um");
		return eqp; 
	}
	
	private static Modalidade carregarModalidade(){
		Modalidade mod = new Modalidade();
		List<TipoPonto> lista = new ArrayList<TipoPonto>();
		lista.add(ponto);
		mod.setDescricao("Esporte Coletivo de até 11 jogadores.");
		mod.setNome("Futebol De Quadra");
		mod.setNumMaxJogador(21);
		mod.setNumMinJogador(11);
		return mod;
	}

	private  static EventoModalidade carregarEventoModalidade(){
		EventoModalidade mod = new EventoModalidade();
		mod.setEvento(carregarEvento());
		mod.setModalidade(carregarModalidade());
		mod.setIdadeMaximaPermitida(15);
		mod.setSexo('F');
		return mod;
	}

	private static TipoPonto carregarTipoPonto() {
		TipoPonto ponto = new TipoPonto();
		ponto.setNome("gol");
		ponto.setValor(1);
		return ponto;
	}
	
	private static Time carregarTime() {
		Time t= new Time();
		t.setEquipe(equipe);
		t.setEventoModalidade(carregarEventoModalidade());
		t.setNumDerrota(0);
		t.setNumEmpate(0);
		t.setNumVitoria(0);
		t.setPontuacao(0);
		return t;
	}
}
