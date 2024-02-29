package br.com.aguaboaservicos.pedido.itemPedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemPedidoCadastro(@NotNull(message = "Id do produto é obrigatório") Long produtoId,
                                 @NotNull(message = "Campo quantidade é obrigatório") @Min(value = 0,
                                         message = "Quantidade inválida") BigDecimal quantidade,
                                 BigDecimal desconto,
                                 BigDecimal precoUnitario) {

}
