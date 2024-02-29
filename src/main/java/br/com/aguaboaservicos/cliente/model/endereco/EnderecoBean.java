package br.com.aguaboaservicos.cliente.model.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoBean(@NotBlank(message = "Campo logradouro é obrigatório!") String logradouro,

		@NotNull(message = "Campo número é obrigatório!") String numero,

		String bairro,

		String complemento,

		@Pattern(regexp = "(\\d{5}-\\d{3})?")
		String cep,

		String cidade) {

	public EnderecoBean(Endereco endereco) {
		this(endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(), endereco.getComplemento(),
				endereco.getCep(), endereco.getCidade());
	}
}
