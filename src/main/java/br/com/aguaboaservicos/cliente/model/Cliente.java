package br.com.aguaboaservicos.cliente.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.aguaboaservicos.cliente.model.endereco.Endereco;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	private String celular;

	private String telefone;

	@Column(name = "saldo_em_conta")
	private BigDecimal saldoEmConta = BigDecimal.ZERO;

	private boolean ativo = true;

	@Column(name = "data_de_criacao")
	private LocalDateTime dataDeCriacao = LocalDateTime.now();

	public Cliente(ClienteCadastro cliente) {
		this.nome = cliente.nome();
		this.endereco = new Endereco(cliente.endereco());
		this.telefone = cliente.telefone();

		if (cliente.celular() != null) {
			this.celular = cliente.celular();
		}
	}

	public void atualizaCliente(ClienteAtualizacao cliente) {
		if (cliente.nome() != null) {
			this.nome = cliente.nome();
		}

		if (cliente.telefone() != null) {
			this.telefone = cliente.telefone();
		}

		if (cliente.celular() != null) {
			this.celular = cliente.celular();
		}

		if (cliente.endereco() != null) {
			this.endereco.atualizaEndereco(cliente.endereco());
		}
	}

	public void desativaCliente() {
		this.ativo = false;
	}
}
