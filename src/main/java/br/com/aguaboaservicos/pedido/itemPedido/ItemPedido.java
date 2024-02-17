package br.com.aguaboaservicos.pedido.itemPedido;

import br.com.aguaboaservicos.common.utils.NumberUtils;
import br.com.aguaboaservicos.pedido.model.Pedido;
import br.com.aguaboaservicos.produto.ProdutoService;
import br.com.aguaboaservicos.produto.model.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	private BigDecimal desconto = BigDecimal.ZERO;
	
	private BigDecimal quantidade = BigDecimal.ONE;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	private boolean ativo = true;

	public ItemPedido(Pedido pedido, ProdutoService produtoService, ItemPedidoCadastro dadosCadastro) {
		this.pedido = pedido;
		
		this.produto = produtoService.buscaEntidadeProduto(dadosCadastro.produtoId());
		
		this.quantidade = dadosCadastro.quantidade();
		
		if (NumberUtils.isNotEmpty(dadosCadastro.desconto())) {
			this.desconto = dadosCadastro.desconto();
		}
		
		if (NumberUtils.isNotEmpty(dadosCadastro.precoUnitario())) {
			this.precoUnitario = dadosCadastro.precoUnitario();
		} else {
			this.precoUnitario = this.produto.getPreco();
		}
	}

	public void cancelaItem() {
		this.ativo = false;
	}
}
