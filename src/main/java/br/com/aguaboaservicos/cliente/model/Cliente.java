package br.com.aguaboaservicos.cliente.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.aguaboaservicos.cliente.model.endereco.Endereco;
import br.com.aguaboaservicos.common.utils.StringUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Endereco endereco;

	private String contato;

	@Column(name = "saldo_em_conta")
	private BigDecimal saldoEmConta = BigDecimal.ZERO;

	private boolean ativo = true;

	@Column(name = "data_de_criacao")
	private LocalDateTime dataDeCriacao = LocalDateTime.now();

	public Cliente(ClienteCadastro cliente) {
		this.nome = cliente.nome();
		this.endereco = new Endereco(cliente.endereco());

		if (StringUtils.isOnlyNumbers(cliente.contato())) {			
			this.contato = cliente.contato();
		}
	}

	public void alteraCliente(ClienteAlteracao cliente) {
		if (StringUtils.isNotEmpty(cliente.nome())) {
			this.nome = cliente.nome();
		}

		if (StringUtils.isNotEmpty(cliente.contato()) && StringUtils.isOnlyNumbers(cliente.contato())) {
			this.contato = cliente.contato();
		}

		if (cliente.endereco() != null) {
			this.getEndereco().atualizaEndereco(cliente.endereco());
		}

	}

	public void desativaCliente() {
		this.ativo = false;
		this.endereco.setAtivo(false);
	}
}
