package br.com.aguaboaservicos.produto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoCadastro(@NotBlank(message = "Campo nome é obrigatório") String nome, String marca,
                              @NotNull(message = "Campo preço é obrigatório") BigDecimal preco) {
}
