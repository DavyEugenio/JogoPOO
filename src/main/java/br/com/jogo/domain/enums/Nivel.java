package br.com.jogo.domain.enums;

public enum Nivel {
	FACIL(1, "ROLE_Facil"), PADRAO(2, "ROLE_PADRAO");

	private int cod;
	private String descricao;

	Nivel(int code, String descricao) {
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Nivel toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Nivel x : Nivel.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
