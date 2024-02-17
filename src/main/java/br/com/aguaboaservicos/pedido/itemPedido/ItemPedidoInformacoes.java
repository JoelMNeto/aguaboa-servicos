package br.com.aguaboaservicos.pedido.itemPedido;

import br.com.aguaboaservicos.produto.model.ProdutoInformacoes;

import java.math.BigDecimal;

public record ItemPedidoInformacoes(Long id,
                                    ProdutoInformacoes produto,
                                    BigDecimal quantidade,
                                    BigDecimal desconto,
                                    BigDecimal precoUnitario) {

    public ItemPedidoInformacoes(ItemPedido pedido) {
        this(pedido.getId(), new ProdutoInformacoes(pedido.getProduto()), pedido.getQuantidade(), pedido.getDesconto(),
                pedido.getPrecoUnitario());
    }

}
