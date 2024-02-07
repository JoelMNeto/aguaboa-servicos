package br.com.aguaboaservicos.produto.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.aguaboaservicos.utils.StringUtils;
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
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String marca;
	
	private BigDecimal preco;
	
	private boolean ativo = true;
	
	@Column(name = "data_de_criacao")
	private LocalDate dataDeCriacao = LocalDate.now();
	
	public Produto(ProdutoCadastro dadosCadastro) {
		this.nome = dadosCadastro.nome();
		this.preco = dadosCadastro.preco();
		
		if (StringUtils.isNotEmpty(dadosCadastro.marca())) {
			this.marca = dadosCadastro.marca();
		}
	}
	
	public void alteraProduto(ProdutoAlteracao dadosAlteracao) {
		if (StringUtils.isNotEmpty(dadosAlteracao.nome())) {
			this.nome = dadosAlteracao.nome();
		}
		
		if (dadosAlteracao.preco() != null) {
			this.preco = dadosAlteracao.preco();
		}
		
		if (StringUtils.isNotEmpty(dadosAlteracao.marca())) {
			this.marca = dadosAlteracao.marca();
		}
	}
	
	public void desativaProduto() {
		this.ativo = false;
	}
}
