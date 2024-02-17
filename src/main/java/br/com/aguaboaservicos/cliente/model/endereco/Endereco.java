package br.com.aguaboaservicos.cliente.model.endereco;

import java.time.LocalDateTime;

import br.com.aguaboaservicos.common.utils.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	private String cidade = "Araraquara";

	@Column(name = "data_de_criacao")
	private LocalDateTime dataDeCriacao = LocalDateTime.now();

	private boolean ativo = true;

	public Endereco(EnderecoBean endereco) {
		this.logradouro = endereco.logradouro();
		this.numero = endereco.numero();

		if (StringUtils.isNotEmpty(endereco.bairro())) {
			this.bairro = endereco.bairro();
		}

		if (StringUtils.isNotEmpty(endereco.cep())) {
			this.cep = endereco.cep();
		}

		if (StringUtils.isNotEmpty(endereco.cidade())) {
			this.cidade = endereco.cidade();
		}

		if (StringUtils.isNotEmpty(endereco.complemento())) {
			this.complemento = endereco.complemento();
		}
	}

	public void atualizaEndereco(EnderecoBean endereco) {
		if (StringUtils.isNotEmpty(endereco.logradouro())) {
			this.logradouro = endereco.logradouro();
		}

		if (StringUtils.isNotEmpty(endereco.numero())) {
			this.numero = endereco.numero();
		}

		if (StringUtils.isNotEmpty(endereco.bairro())) {
			this.bairro = endereco.bairro();
		}

		if (StringUtils.isNotEmpty(endereco.cep())) {
			this.cep = endereco.cep();
		}

		if (StringUtils.isNotEmpty(endereco.cidade())) {
			this.cidade = endereco.cidade();
		}

		if (StringUtils.isNotEmpty(endereco.complemento())) {
			this.complemento = endereco.complemento();
		}
	}

}
