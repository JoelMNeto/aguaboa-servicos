package br.com.aguaboaservicos.pedido.model;

import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoCadastro;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record PedidoAlteracao(@NotNull(message = "Id é obrigatório") Long id,

                              BigDecimal valorPago,
                              List<ItemPedidoCadastro> itens) {
}
