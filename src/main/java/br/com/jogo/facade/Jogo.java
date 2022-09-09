package br.com.jogo.facade;

import java.util.HashSet;
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
import br.com.jogo.dto.AlternativaNewDTO;
import br.com.jogo.dto.CategoriaNewDTO;
import br.com.jogo.dto.ConfiguracaoPartidaNewDTO;
import br.com.jogo.dto.ItemDTO;
import br.com.jogo.dto.ItemNewDTO;
import br.com.jogo.dto.JogadorNewDTO;
import br.com.jogo.dto.QuestaoDTO;
import br.com.jogo.dto.QuestaoNewDTO;
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

	public Admin insertAdmin(JogadorNewDTO objDto) {
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

	public Alternativa insertAlternativa(AlternativaNewDTO objDto) {
		Alternativa obj = alternativaService.fromDTO(objDto);
		return alternativaService.insert(obj);
	}

	public Alternativa findAlternativa(Integer id) {
		return alternativaService.find(id);
	}

	public List<Alternativa> findAllAlternativa() {
		return alternativaService.findAll();
	}

	public void deleteAlternativa(Integer id) {
		alternativaService.delete(id);
	}

	public Alternativa updateAlternativa(AlternativaDTO objDto) {
		return alternativaService.update(obj);
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
	}/*
		 * 
		 * public Categoria updateCategoria(CategoriaDTO objDto) { Categoria obj =
		 * categoriaService.fromDTO(objDto);
		 * obj.setCategoria(categoriaService.find(objDto.getCategoria().getId()));
		 * return categoriaService.update(obj); }
		 */

	// --------------------------------ConfiguracaoPartida----------------------------------------------
	public ConfiguracaoPartida insertConfiguracaoPartida(ConfiguracaoPartidaNewDTO objDto) {
		Jogador jog = null;
		Set<Questao> quest = new HashSet<>();
		quest.addAll(objDto.getQuestoes().stream().map(x -> questaoService.find(x)).toList());
		ConfiguracaoPartida obj = configuracaoPartidaService.fromDTO(objDto, jog, quest);
		return configuracaoPartidaService.insert(obj);
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
		obj.setCategoria(categoriaService.find(objDto.getCategoria().getId()));
		return questaoService.update(obj);
	}

	// -------------------------------------RegistroPartida-----------------------------------------
	
	public RegistroPartida insertRegistroPartida(RegistroPartidaNewDTO objDto) {
		RegistroPartida obj = registroPartidaServicefromDTO(objDto);
		return registroPartidaService.insert(obj);
	}
	
	public RegistroPartida findRegistroPartida(Integer id) {
		return registroPartidaService.find(id);
	}
	
	public RegistroPartida updateRegistroPartida(RegistroPartidaDTO objDto) {
		RegistroPartida obj = registroPartidaServicefromDTO(objDto);
		return registroPartidaService.update(obj);
	}
	
	public void deleteRegistroPartida(Integer id) {
		registroPartidaService.delete(id);
	}
	
	public List<RegistroPartida> findAllRegistroPartidas() {
		return registroPartidaService.findAll();
	}
}
