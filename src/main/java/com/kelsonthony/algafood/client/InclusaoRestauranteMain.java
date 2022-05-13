package com.kelsonthony.algafood.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.kelsonthony.algafood.client.api.ClientApiException;
import com.kelsonthony.algafood.client.api.RestauranteClient;
import com.kelsonthony.algafood.client.model.RestauranteModel;
import com.kelsonthony.algafood.client.model.input.CidadeInput;
import com.kelsonthony.algafood.client.model.input.CozinhaInput;
import com.kelsonthony.algafood.client.model.input.EnderecoInput;
import com.kelsonthony.algafood.client.model.input.RestaraunteInput;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		try {
			
			var restTemplate = new RestTemplate();
			var restauranteClient = new RestauranteClient(
					restTemplate, "http://localhost:8080");
			
			var cozinha = new CozinhaInput();
			cozinha.setId(1L);
			
			var cidade = new CidadeInput();
			cidade.setId(1L);
			
			var endereco = new EnderecoInput();
			endereco.setCidade(cidade);
			endereco.setCep("71925360");
			endereco.setLogradouro("Rua 20 Sul");
			endereco.setNumero("12");
			endereco.setBairro("Águas Claras");
			
			var restaurante = new RestaraunteInput();
			restaurante.setNome("Comida de Casa");
			restaurante.setTaxaFrete(new BigDecimal(9.4));
			restaurante.setCozinha(new CozinhaInput());
			restaurante.setCozinha(cozinha);
			restaurante.setAtivo(true);
			restaurante.setAberto(true);
			restaurante.setEndereco(endereco);
			
			RestauranteModel restauranteModel = restauranteClient.adicionar(restaurante);
			
			System.out.println(restauranteModel);
		} catch (ClientApiException e) {
			if(e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
				
				e.getProblem().getObjects().stream()
					.forEach(p -> System.out.println("- " + p.getUserMessage()));
			} else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}
	}

}
