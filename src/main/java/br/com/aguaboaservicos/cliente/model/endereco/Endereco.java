package br.com.aguaboaservicos.cliente.model.endereco;

import java.time.LocalDateTime;

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

	private String bairro;

	private String cep;

	private String cidade = "Araraquara";
	
	@Column(name = "data_de_criacao")
	private LocalDateTime dataDeCriacao = LocalDateTime.now();

	public Endereco(EnderecoBean endereco) {
		this.logradouro = endereco.logradouro();
		this.numero = endereco.numero();

		if (endereco.bairro() != null) {
			this.bairro = endereco.bairro();
		}

		if (endereco.cep() != null) {
			this.cep = endereco.cep();
		}

		if (endereco.cidade() != null) {
			this.cidade = endereco.cidade();
		}
	}

	public void atualizaEndereco(EnderecoBean endereco) {
		if (endereco.logradouro() != null) {
			this.logradouro = endereco.logradouro();
		}

		if (endereco.numero() != null) {
			this.numero = endereco.numero();
		}

		if (endereco.bairro() != null) {
			this.bairro = endereco.bairro();
		}

		if (endereco.cep() != null) {
			this.cep = endereco.cep();
		}

		if (endereco.cidade() != null) {
			this.cidade = endereco.cidade();
		}
	}

}
