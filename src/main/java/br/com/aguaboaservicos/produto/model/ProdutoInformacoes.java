package br.com.aguaboaservicos.produto.model;

import java.math.BigDecimal;

public record ProdutoInformacoes(Long id, String nome, String marca, BigDecimal preco) {

    public ProdutoInformacoes(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getMarca(), produto.getPreco());
    }
}
