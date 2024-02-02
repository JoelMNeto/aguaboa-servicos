package br.com.aguaboaservicos.cliente.model;

import br.com.aguaboaservicos.cliente.model.endereco.EnderecoBean;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteCadastro(
		@NotBlank(message = "Campo nome é obrigatório!")
		String nome,
		
		@NotNull
		@Valid
		EnderecoBean endereco,
		
		@NotBlank(message = "Campo telefone é obrigatório!")
		String telefone,
		
		String celular) {}