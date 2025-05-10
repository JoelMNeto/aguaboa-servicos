package br.com.aguaboaservicos.pedido.model;

import br.com.aguaboaservicos.pedido.enumerations.FormaPagamentoEnum;
import br.com.aguaboaservicos.pedido.enumerations.TipoPedidoEnum;
import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoCadastro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record PedidoLancamento(@NotNull(message = "Campo cliente é obrigatório") Long clienteId,
                               @NotNull @Valid List<ItemPedidoCadastro> itens,
                               BigDecimal frete, TipoPedidoEnum tipo,
                               FormaPagamentoEnum formaPagamento, BigDecimal valorPago) {
}
