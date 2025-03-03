package br.com.aguaboaservicos.pedido.model;

import br.com.aguaboaservicos.cliente.model.ClienteInformacoes;
import br.com.aguaboaservicos.pedido.enumerations.FormaPagamentoEnum;
import br.com.aguaboaservicos.pedido.enumerations.StatusEnum;
import br.com.aguaboaservicos.pedido.enumerations.TipoPedidoEnum;
import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoInformacoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoInformacoes(Long id, BigDecimal valorTotal, BigDecimal frete, StatusEnum status,
                                TipoPedidoEnum tipo, BigDecimal valorAtualizado, BigDecimal valorPago,
                                FormaPagamentoEnum formaPagamento, LocalDateTime data, ClienteInformacoes cliente,
                                List<ItemPedidoInformacoes> itens) {

    public PedidoInformacoes(Pedido pedido) {
        this(pedido.getId(), pedido.getValorTotal(), pedido.getFrete(), pedido.getStatus(), pedido.getTipoDoPedido(),
                pedido.getValorAtualizado(), pedido.getValorPago(),
                pedido.getFormaPagamento(), pedido.getDataDeCriacao(), new ClienteInformacoes(pedido.getCliente()),
                pedido.getItensPedido().stream().map(ItemPedidoInformacoes::new).toList());
    }
}
