package br.com.aguaboaservicos.produto.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoAlteracao(@NotNull(message = "Id é obrigatório") Long id, String nome, String marca,
                               @Min(value = 1,
                                       message = "Preço inválido") BigDecimal preco) {
}
