package br.com.jogo.facade;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jogo.domain.Admin;
import br.com.jogo.domain.Alternativa;
import br.com.jogo.domain.Categoria;
import br.com.jogo.domain.ConfiguracaoPartida;
import br.com.jogo.domain.Item;
import br.com.jogo.domain.Jogador;
import br.com.jogo.domain.Questao;
import br.com.jogo.domain.RegistroPartida;
import br.com.jogo.services.AdminService;
import br.com.jogo.services.AlternativaService;
import br.com.jogo.services.CategoriaService;
import br.com.jogo.services.ConfiguracaoPartidaService;
import br.com.jogo.services.ItemService;
import br.com.jogo.services.JogadorService;
import br.com.jogo.services.QuestaoService;
import br.com.jogo.services.RegistroPartidaService;

@Component
public class Jogo {
	@Autowired
	private AdminService adminService;
	@Autowired
	private AlternativaService alternativaService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private ConfiguracaoPartidaService configuracaoPartidaService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private JogadorService jogadorService;
	@Autowired
	private QuestaoService questaoService;
	@Autowired
	private RegistroPartidaService registroPartidaService;

	// --------------------------------Admin----------------------------------------------

	public Admin findAdmin(Integer id) {
		return adminService.find(id);
	}

	public Admin insertAdmin(Admin obj) {
		return adminService.insert(obj);
	}

	public Admin updateAdmin(Admin obj) {
		return adminService.update(obj);
	}

	public void deleteAdmin(Integer id) {
		adminService.delete(id);
	}

	public List<Admin> findAllAdmins() {
		return adminService.findAll();
	}

	// --------------------------------Alternativa----------------------------------------------

	public Alternativa findAlternativa(Integer id) {
		return alternativaService.find(id);
	}

	// --------------------------------Categoria----------------------------------------------

	public Categoria insertCategoria(Categoria obj) {
		return categoriaService.insert(obj);
	}

	public Categoria findCategoria(Integer id) {
		return categoriaService.find(id);
	}

	public List<Categoria> findAllCategorias() {
		return categoriaService.findAll();
	}

	public void deleteCategoria(Integer id) {
		categoriaService.delete(id);
	}

	public Categoria updateCategoria(Categoria obj) {
		return categoriaService.update(obj);
	}

	// --------------------------------ConfiguracaoPartida----------------------------------------------
	public ConfiguracaoPartida insertConfiguracaoPartida(ConfiguracaoPartida obj) {
		// Jogador do token
		Jogador jog = null;
		obj.setJogador(jog);
		if (obj.getQuestoes() != null) {
			obj = new ConfiguracaoPartida(jog,
					obj.getQuestoes().stream().map(x -> questaoService.find(x.getId())).collect(Collectors.toSet()));
		} else {
			obj = new ConfiguracaoPartida(jog, obj.getNivel());
			if (obj.getCategorias() != null) {
				obj.setCategorias(obj.getCategorias().stream().map(x -> categoriaService.find(x.getId()))
						.collect(Collectors.toSet()));
			}
			obj.setQuestoes(Set.of(nextQuestionConfiguracaoPartida(obj)));
		}
		return configuracaoPartidaService.insert(obj);
	}

	public ConfiguracaoPartida findConfiguracaoPartida(Integer id) {
		return configuracaoPartidaService.find(id);
	}

	public List<ConfiguracaoPartida> findAllConfiguracaoPartidas() {
		return configuracaoPartidaService.findAll();
	}

	public void deleteConfiguracaoPartida(Integer id) {
		configuracaoPartidaService.delete(id);
	}

	// --------------------------------Item----------------------------------------------

	public Item findItem(Integer id) {
		return itemService.find(id);
	}

	public Item insertItem(Item obj) {
		return itemService.insert(obj);
	}

	public Item updateItem(Item obj) {
		return itemService.update(obj);
	}

	public void deleteItem(Integer id) {
		itemService.delete(id);
	}

	public List<Item> findAllItens() {
		return itemService.findAll();
	}

	// --------------------------------Jogador----------------------------------------------

	public Jogador findJogador(Integer id) {
		return jogadorService.find(id);
	}

	public Jogador insertJogador(Jogador obj) {
		return jogadorService.insert(obj);
	}

	public Jogador updateJogador(Jogador obj) {
		return jogadorService.update(obj);
	}

	public void deleteJogador(Integer id) {
		jogadorService.delete(id);
	}

	public List<Jogador> findAllJogadores() {
		return jogadorService.findAll();
	}

	// --------------------------------Questao----------------------------------------------

	public Questao insertQuestao(Questao obj) {
		obj.setCategoria(categoriaService.find(obj.getCategoria().getId()));
		return questaoService.insert(obj);
	}

	public Questao findQuestao(Integer id) {
		return questaoService.find(id);
	}

	public List<Questao> findAllQuestoes() {
		return questaoService.findAll();
	}

	public void deleteQuestao(Integer id) {
		questaoService.delete(id);
	}

	public Questao updateQuestao(Questao obj) {
		obj.setCategoria(categoriaService.find(obj.getCategoria().getId()));
		List<Alternativa> alts = obj.getAlternativas().stream().toList();
		if (alts != null) {
			obj.setAlternativas(alternativaService.updateAllByList(obj.getAlternativas(), alts));
		}
		return questaoService.update(obj);
	}

	// -------------------------------------RegistroPartida-----------------------------------------

	public RegistroPartida insertRegistroPartida(RegistroPartida obj) {
		Questao ultima = null;
		// Jogador Logado
		Jogador jog = jogadorService.find(1);
		if (obj.getConfiguracaoPartida() != null) {
			ConfiguracaoPartida cp = findConfiguracaoPartida(obj.getConfiguracaoPartida().getId());
			ultima = cp.getQuestoes().stream().findAny().get();
			obj = new RegistroPartida(cp, jog);
			
		} else {
			ConfiguracaoPartida cp = new ConfiguracaoPartida(jog);
			ultima = nextQuestionConfiguracaoPartida(cp);
			cp.setQuestoes(Set.of(ultima));
			obj = new RegistroPartida(cp, jog);
		}
		obj.setUltimaQuestao(ultima);
		return registroPartidaService.insert(obj);
	}

	public RegistroPartida findRegistroPartida(Integer id) {
		return registroPartidaService.find(id);
	}

	public void deleteRegistroPartida(Integer id) {
		registroPartidaService.delete(id);
	}

	public List<RegistroPartida> findAllRegistroPartidas() {
		return registroPartidaService.findAll();
	}

	// -----------------------------Regras Jogo-----------------------------

	public RegistroPartida answerQuestion(RegistroPartida registroPartida, Alternativa alternativa) {
		RegistroPartida rp = findRegistroPartida(registroPartida.getId());
		if (!rp.isAtiva()) {
			// Excecao: partida inativo
		}
		ConfiguracaoPartida cp = rp.getConfiguracaoPartida();
		Alternativa a = findAlternativa(alternativa.getId());
		Questao q = questaoService.findByAlternativa(a);
		if (!rp.getUltimaQuestao().equals(q)) {
			// Excecao: alternativa não pertence a uma questoa da configuracao
		}
		
		if (a.isCorreta()) {
			rp.addPontuacao(q.getNivel());
			rp.getJogador().addPontuacao(q.getNivel());
			rp.addQuestoes(q);
			Questao nextQ = nextQuestionRegistroPartida(rp);
			if (nextQ == null) {
				rp.setAtiva(false);
			} else {
				if (!cp.isPredefinida()) {
					cp.addQuestao(nextQ);
				}
			}
			rp.setUltimaQuestao(nextQ);
		} else {
			rp.setAtiva(false);
		}
		rp = registroPartidaService.update(rp);
		if (!a.isCorreta()) {
			// Excecao: Resposta Incorreta;
		}
		return rp;
	}

	private Questao nextQuestionRegistroPartida(RegistroPartida obj) {
		ConfiguracaoPartida cp = obj.getConfiguracaoPartida();
		Questao nextQ = null;
		if (!cp.isPredefinida()) {
			nextQ = nextQuestionConfiguracaoPartida(cp);
		} else {
			Set<Questao> sq = cp.getQuestoes();
			sq.removeAll(obj.getQuestoesRepondias());
			if (!sq.isEmpty()) {
				nextQ = sq.stream().findAny().get();
			} else {
				//Excecao: Não questoes disponiveis
			}
		}
		return nextQ;
	}

	private Questao nextQuestionConfiguracaoPartida(ConfiguracaoPartida obj) {
		Questao nextQ = null;
		if (obj.getNivel() != 0 && obj.getCategorias() != null) {
			nextQ = questaoService.findOneByNivelAndCategoria(obj.getNivel(), obj.getCategorias(), obj.getQuestoes());
		} else if (obj.getNivel() != 0) {
			nextQ = questaoService.findOneByNivel(obj.getNivel(), obj.getQuestoes());
		} else if (obj.getCategorias() != null) {
			nextQ = questaoService.findOneByCategoria(obj.getCategorias(), obj.getQuestoes());
		} else {
			nextQ = questaoService.findOneNotIn(obj.getQuestoes());
		}
		return nextQ;
	}

	public List<RegistroPartida> findPartidaAtivaByJogador(Jogador obj) {
		return registroPartidaService.findAtivaByJogador(obj);
	}

}
