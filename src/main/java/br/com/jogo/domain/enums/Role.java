package br.com.jogo.domain.enums;

public enum Role {
	ADMIN(1, "ROLE_ADMIN"),
	JOGADOR(2, "ROLE_JOGADOR");

	private int cod;
	private String descricao;

	private Role(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Role toEnum(Integer cod) throws IllegalArgumentException {
		if (cod == null) {
			return null;
		}

		for (Role x : Role.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
