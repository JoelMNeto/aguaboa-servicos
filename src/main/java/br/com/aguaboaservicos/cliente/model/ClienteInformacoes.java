package br.com.aguaboaservicos.cliente.model;

import java.math.BigDecimal;

import br.com.aguaboaservicos.cliente.model.endereco.EnderecoBean;

public record ClienteInformacoes(
		Long id,

		String nome,

		String telefone,

		String celular,

		EnderecoBean endereco,

		BigDecimal saldoEmConta) {

	public ClienteInformacoes(Cliente cliente) {
		this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getCelular(), new EnderecoBean(cliente.getEndereco()),
				cliente.getSaldoEmConta());
	}

}
