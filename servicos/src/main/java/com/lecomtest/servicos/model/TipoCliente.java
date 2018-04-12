package com.lecomtest.servicos.model;

public enum TipoCliente {

	OURO("Ouro"), PRATA("Prata");

	private String descricao;

	TipoCliente(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
