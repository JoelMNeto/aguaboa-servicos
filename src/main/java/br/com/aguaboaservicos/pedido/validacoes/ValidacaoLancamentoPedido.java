package br.com.aguaboaservicos.pedido.validacoes;

import br.com.aguaboaservicos.pedido.model.PedidoLancamento;

public interface ValidacaoLancamentoPedido {

    void validar(PedidoLancamento dadosLancamento);

}
