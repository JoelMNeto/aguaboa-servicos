package br.com.aguaboaservicos.produto.model;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoAlteracao(@NotNull Long id, String nome, String marca, BigDecimal preco) {
}
