package br.com.aguaboaservicos.pedido.enumerations;

import lombok.Getter;

@Getter
public enum TipoPedidoEnum {
	ENTREGA("ENTREGA"),
	RETIRADA("RETIRADA");

    private final String descricao;

    TipoPedidoEnum(String descricao) {
        this.descricao = descricao;
    }
}
