package br.com.aguaboaservicos.pedido.validacoes;

import br.com.aguaboaservicos.common.utils.NumberUtils;
import br.com.aguaboaservicos.pedido.enumerations.TipoPedidoEnum;
import br.com.aguaboaservicos.pedido.model.PedidoLancamento;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTipoFrete implements ValidacaoLancamentoPedido {

    @Override
    public void validar(PedidoLancamento dadosLancamento) {
        if (NumberUtils.isEmpty(dadosLancamento.frete())) {
            return;
        }

        if (dadosLancamento.tipo() == TipoPedidoEnum.RETIRADA) {
            throw new RuntimeException("Um pedido para retirada não pode possuir valor de frete!");
        }
    }

}
