package br.com.aguaboaservicos.pedido.enumerations;

import lombok.Getter;

@Getter
public enum FormaPagamentoEnum {
    CARTAO("CARTÃO"),
    DINHEIRO("DINHEIRO"),
    SALDO("SALDO EM CONTA"),
    PIX("PIX");

    private final String descricao;

    FormaPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }
}
