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
import br.com.jogo.dto.AdminDTO;
import br.com.jogo.dto.CategoriaDTO;
import br.com.jogo.dto.CategoriaNewDTO;
import br.com.jogo.dto.ConfiguracaoPartidaNewDTO;
import br.com.jogo.dto.ItemDTO;
import br.com.jogo.dto.ItemNewDTO;
import br.com.jogo.dto.JogadorDTO;
import br.com.jogo.dto.JogadorNewDTO;
import br.com.jogo.dto.QuestaoDTO;
import br.com.jogo.dto.QuestaoNewDTO;
import br.com.jogo.dto.RegistroPartidaNewDTO;
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

	public Admin insertAdmin(AdminDTO objDto) {
		Admin obj = adminService.fromDTO(objDto);
		return adminService.insert(obj);
	}

	public Admin updateAdmin(AdminDTO objDto) {
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
		Set<Questao> quest = objDto.getQuestoes().stream().map(x -> questaoService.find(x)).collect(Collectors.toSet());
		ConfiguracaoPartida obj = configuracaoPartidaService.fromDTO(objDto);
		obj.setJogador(jog);
		obj.setQuestoes(quest);
		return configuracaoPartidaService.insert(obj);
	}

	public ConfiguracaoPartida findConfiguracaoPartida(Integer id) {
		return configuracaoPartidaService.find(id);
	}

	public List<ConfiguracaoPartida> findAllConfiguracaoPartida() {
		return configuracaoPartidaService.findAll();
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

	public Jogador insertJogador(JogadorNewDTO objDto) {
		Jogador obj = jogadorService.fromDTO(objDto);
		return jogadorService.insert(obj);
	}

	public Jogador updateJogador(JogadorDTO objDto) {
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
		ConfiguracaoPartida cp = findConfiguracaoPartida(objDto.getConfiguracaoPartida());
		if (cp != null) {
			obj = new RegistroPartida(cp);
		} else {
			obj = new RegistroPartida(new ConfiguracaoPartida());
		}
		obj = new RegistroPartida(null);
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
}
