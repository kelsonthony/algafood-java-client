package com.kelsonthony.algafood.client.model.input;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestaraunteInput {

	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaInput cozinha;
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoInput endereco;
}
