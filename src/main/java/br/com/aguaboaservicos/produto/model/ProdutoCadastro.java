package br.com.aguaboaservicos.produto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoCadastro(@NotBlank String nome, String marca, @NotNull BigDecimal preco) {
}
