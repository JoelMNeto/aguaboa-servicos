package br.com.aguaboaservicos.cliente.model;

import br.com.aguaboaservicos.cliente.model.endereco.EnderecoBean;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteCadastro(
		@NotBlank(message = "Campo nome é obrigatório!")
		String nome,
		
		@NotNull
		@Valid
		EnderecoBean endereco,
		
		@NotBlank(message = "Campo contato é obrigatório!")
		@Pattern(regexp = "(\\+\\d{1,3})?(\\(\\d{2}\\))?\\d{4,5}-\\d{4}")
		String contato
) {}