package br.com.aguaboaservicos.cliente.model;

import br.com.aguaboaservicos.cliente.model.endereco.EnderecoBean;
import jakarta.validation.constraints.NotNull;

public record ClienteAlteracao(
		@NotNull(message = "Campo id é obrigatório!")
		Long id,
		
		String nome,
		
		EnderecoBean endereco,
		
		String contato) {}
