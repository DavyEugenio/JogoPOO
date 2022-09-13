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
import br.com.jogo.dto.CategoriaDTO;
import br.com.jogo.dto.CategoriaNewDTO;
import br.com.jogo.dto.ConfiguracaoPartidaDTO;
import br.com.jogo.dto.ConfiguracaoPartidaNewDTO;
import br.com.jogo.dto.ItemDTO;
import br.com.jogo.dto.ItemNewDTO;
import br.com.jogo.dto.QuestaoDTO;
import br.com.jogo.dto.QuestaoNewDTO;
import br.com.jogo.dto.RegistroPartidaDTO;
import br.com.jogo.dto.RegistroPartidaNewDTO;
import br.com.jogo.dto.RespostaDTO;
import br.com.jogo.dto.UsuarioDTO;
import br.com.jogo.dto.UsuarioNewDTO;
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

	public Admin insertAdmin(UsuarioNewDTO objDto) {
		Admin obj = adminService.fromDTO(objDto);
		return adminService.insert(obj);
	}

	public Admin updateAdmin(UsuarioDTO objDto) {
		Admin obj = adminService.fromDTO(objDto);
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

	public Categoria insertCategoria(CategoriaNewDTO objDto) {
		Categoria obj = categoriaService.fromDTO(objDto);
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

	public Categoria updateCategoria(CategoriaDTO objDto) {
		Categoria obj = categoriaService.fromDTO(objDto);
		return categoriaService.update(obj);
	}

	// --------------------------------ConfiguracaoPartida----------------------------------------------
	public ConfiguracaoPartida insertConfiguracaoPartida(ConfiguracaoPartidaNewDTO objDto) {
		// Jogador do token
		Jogador jog = null;
		ConfiguracaoPartida obj;
		if (objDto.getQuestoes() != null) {
			obj = new ConfiguracaoPartida(jog,
					objDto.getQuestoes().stream().map(x -> questaoService.find(x)).collect(Collectors.toSet()));
		} else {
			obj = new ConfiguracaoPartida(jog, objDto.getNivel());
			if (objDto.getCategorias() != null) {
				obj.setCategorias(
						objDto.getCategorias().stream().map(x -> categoriaService.find(x)).collect(Collectors.toSet()));
			}
			obj.addQuestao(nextQuestionConfiguracaoPartida(obj));
		}
		return configuracaoPartidaService.insert(obj);
	}

	public ConfiguracaoPartida findConfiguracaoPartida(Integer id) {
		return configuracaoPartidaService.find(id);
	}

	public List<ConfiguracaoPartidaDTO> findAllConfiguracaoPartidas() {
		return configuracaoPartidaService.findAll().stream()
				.map(obj -> new ConfiguracaoPartidaDTO(obj.getId(), obj.getNivel(),
						obj.getQuestoes().stream().map(q -> new QuestaoDTO(q)).collect(Collectors.toSet()),
						obj.getCategorias().stream().map(c -> new CategoriaDTO(c)).collect(Collectors.toSet())))
				.toList();
	}

	public void deleteConfiguracaoPartida(Integer id) {
		configuracaoPartidaService.delete(id);
	}

	// --------------------------------Item----------------------------------------------

	public Item findItem(Integer id) {
		return itemService.find(id);
	}

	public Item insertItem(ItemNewDTO objDto) {
		Item obj = itemService.fromDTO(objDto);
		return itemService.insert(obj);
	}

	public Item updateItem(ItemDTO objDto) {
		Item obj = itemService.fromDTO(objDto);
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

	public Jogador insertJogador(UsuarioNewDTO objDto) {
		Jogador obj = jogadorService.fromDTO(objDto);
		return jogadorService.insert(obj);
	}

	public Jogador updateJogador(UsuarioDTO objDto) {
		Jogador obj = jogadorService.fromDTO(objDto);
		return jogadorService.update(obj);
	}

	public void deleteJogador(Integer id) {
		jogadorService.delete(id);
	}

	public List<Jogador> findAllJogadores() {
		return jogadorService.findAll();
	}

	// --------------------------------Questao----------------------------------------------

	public Questao insertQuestao(QuestaoNewDTO objDto) {
		Questao obj = questaoService.fromDTO(objDto);
		obj.setCategoria(categoriaService.find(objDto.getCategoria()));
		obj.setAlternativas(objDto.getAlternativas().stream().map((a) -> alternativaService.fromDTO(a))
				.collect(Collectors.toSet()));
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

	public Questao updateQuestao(QuestaoDTO objDto) {
		Questao obj = questaoService.fromDTO(objDto);
		obj.setCategoria(categoriaService.find(objDto.getCategoria()));
		obj = questaoService.update(obj);
		if (objDto.getAlternativas() != null) {
			alternativaService.updateAllByList(obj.getAlternativas(),
					objDto.getAlternativas().stream().map(a -> alternativaService.fromDTO(a)).toList());
		}
		return obj;
	}

	// -------------------------------------RegistroPartida-----------------------------------------

	public RegistroPartida insertRegistroPartida(RegistroPartidaNewDTO objDto) {
		RegistroPartida obj;
		if (objDto.getConfiguracaoPartida() != null) {
			ConfiguracaoPartida cp = findConfiguracaoPartida(objDto.getConfiguracaoPartida());
			obj = new RegistroPartida(cp);
		} else {
			// Jogador Logado
			Jogador jog = new Jogador();
			ConfiguracaoPartida cp = new ConfiguracaoPartida(jog);
			obj = new RegistroPartida(cp);
		}
		return registroPartidaService.insert(obj);
	}

	public RegistroPartida findRegistroPartida(Integer id) {
		return registroPartidaService.find(id);
	}

	public void deleteRegistroPartida(Integer id) {
		registroPartidaService.delete(id);
	}

	public List<RegistroPartidaDTO> findAllRegistroPartidas() {
		return registroPartidaService.findAll().stream().map(obj -> new RegistroPartidaDTO(obj)).toList();
	}

	// -----------------------------Regras Jogo-----------------------------

	public RegistroPartida answerQuestion(RespostaDTO objDto) {
		RegistroPartida rp = findRegistroPartida(objDto.getRegistroPartida());
		ConfiguracaoPartida cp = rp.getConfiguracaoPartida();
		Alternativa a = findAlternativa(objDto.getAlternativa());
		Questao q = questaoService.findByAlternativa(a);
		if (!rp.getUltimaQuestao().equals(q)) {
			// Excecao: alternativa não pertence a uma questoa da configuracao
		}
		if (!rp.isAtiva()) {
			// Excecao: partida inativo
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
