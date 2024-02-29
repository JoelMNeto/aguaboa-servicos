package br.com.aguaboaservicos.pedido.model;

import br.com.aguaboaservicos.pedido.enumerations.FormaPagamentoEnum;
import br.com.aguaboaservicos.pedido.enumerations.StatusEnum;
import br.com.aguaboaservicos.pedido.enumerations.TipoPedidoEnum;

import java.time.LocalDateTime;

public record PedidoFiltros(Long clienteId, String busca, StatusEnum status, TipoPedidoEnum tipo, FormaPagamentoEnum formaPagamento,
                            LocalDateTime peridoInicio, LocalDateTime periodoFim) {
}
