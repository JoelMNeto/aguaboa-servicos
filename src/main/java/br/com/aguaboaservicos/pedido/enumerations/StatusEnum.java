package br.com.aguaboaservicos.pedido.enumerations;

import lombok.Getter;

@Getter
public enum StatusEnum {

    PAGO("PAGO"),
    EM_ABERTO("EM ABERTO");

    private final String descricao;

    StatusEnum(String descricao) {
        this.descricao = descricao;
    }
}
