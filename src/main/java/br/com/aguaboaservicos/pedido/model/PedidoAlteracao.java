package br.com.aguaboaservicos.pedido.model;

import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoCadastro;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record PedidoAlteracao(@NotNull(message = "Id é obrigatório") Long id,

                              @Min(value = 0, message = "Valor pago inválido") BigDecimal valorPago,
                              List<ItemPedidoCadastro> itens) {
}
