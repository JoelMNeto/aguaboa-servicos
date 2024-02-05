package br.com.aguaboaservicos.cliente.model.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoBean(@NotBlank(message = "Campo logradouro é obrigatório!") String logradouro,

		@NotNull(message = "Campo número é obrigatório!") String numero,

		String bairro,

		String complemento,

		String cep,

		String cidade) {

	public EnderecoBean(Endereco endereco) {
		this(endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(), endereco.getComplemento(),
				endereco.getCep(), endereco.getCidade());
	}
}
