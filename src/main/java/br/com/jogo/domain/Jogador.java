package br.com.jogo.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Jogador extends Usuario {

	private static final long serialVersionUID = 1L;
	private int qtdPartidas;
	private int pontuacaoTotal;
	private int saldo;
	private LocalDate ultimoAcesso;
	private int qtdAcessosContinuo;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "jogador_id")
	private List<RegistroPartida> partidas;
	@OneToMany(mappedBy = "id.jogador")
	private Set<ItemJogador> itens = new HashSet<>();

	public Jogador() {
	}

	public Jogador(Integer id, String nome, String nomeUsuario, String email, String senha, int pontuacao,
			int numeroPartidas, int pontuacaoTotal, int saldo, LocalDate ultimoAcesso, int qtdAcessosContinuo,
			List<RegistroPartida> partidas) {
		super(id, nome, nomeUsuario, email, senha, 2);
		this.qtdPartidas = numeroPartidas;
		this.pontuacaoTotal = pontuacaoTotal;
		this.saldo = saldo;
		this.qtdAcessosContinuo = qtdAcessosContinuo;
		this.ultimoAcesso = ultimoAcesso;
		this.partidas = partidas;
		
	}

	public Jogador(String nome, String nomeUsuario, String email, String senha) {
		super(nome, nomeUsuario, email, senha, 2);
		qtdPartidas = 0;
		pontuacaoTotal = 0;
		saldo = 0;
		ultimoAcesso = LocalDate.now();
		qtdAcessosContinuo = 0;
	}

	public void registerAccess() {
		LocalDate today = LocalDate.now();
		if (ultimoAcesso.isEqual(today.minusDays(1))) {
			qtdAcessosContinuo++;
		} else if (!ultimoAcesso.isEqual(today)) {
			qtdAcessosContinuo = 0;
		}
		ultimoAcesso = today;
	}
	
	public int getNumeroPartidas() {
		return qtdPartidas;
	}

	public void setNumeroPartidas(int numeroPartidas) {
		this.qtdPartidas = numeroPartidas;
	}

	public int getPontuacaoTotal() {
		return pontuacaoTotal;
	}

	public void addPontuacao(int pontos) {
		this.pontuacaoTotal += pontos;
	}

	public void setPontuacaoTotal(int pontuacaoTotal) {
		this.pontuacaoTotal = pontuacaoTotal;
	}

	public int getQtdPartidas() {
		return qtdPartidas;
	}

	public void setQtdPartidas(int qtdPartidas) {
		this.qtdPartidas = qtdPartidas;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public LocalDate getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(LocalDate ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public int getQtdAcessosContinuo() {
		return qtdAcessosContinuo;
	}

	public void setQtdAcessosContinuo(int qtdAcessosContinuo) {
		this.qtdAcessosContinuo = qtdAcessosContinuo;
	}

}
