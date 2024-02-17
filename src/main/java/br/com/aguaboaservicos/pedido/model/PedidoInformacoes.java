package br.com.aguaboaservicos.pedido.model;

import br.com.aguaboaservicos.cliente.model.ClienteInformacoes;
import br.com.aguaboaservicos.pedido.enumerations.FormaPagamentoEnum;
import br.com.aguaboaservicos.pedido.enumerations.StatusEnum;
import br.com.aguaboaservicos.pedido.enumerations.TipoPedidoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoInformacoes(Long id, BigDecimal valorTotal, BigDecimal frete,
                                TipoPedidoEnum tipo, BigDecimal valorAtualizado,
                                FormaPagamentoEnum formaPagamento, LocalDate data, ClienteInformacoes cliente) {

    public PedidoInformacoes(Pedido pedido) {
        this(pedido.getId(), pedido.getValorTotal(), pedido.getFrete(), pedido.getTipoDoPedido(),
                pedido.getValorAtualizado(),
                pedido.getFormaPagamento(), pedido.getDataDeCriacao(), new ClienteInformacoes(pedido.getCliente()));
    }
}
