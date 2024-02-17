package br.com.aguaboaservicos.pedido.model;

import br.com.aguaboaservicos.pedido.enumerations.FormaPagamentoEnum;
import br.com.aguaboaservicos.pedido.enumerations.StatusEnum;
import br.com.aguaboaservicos.pedido.enumerations.TipoPedidoEnum;

import java.time.LocalDate;

public record PedidoFiltros(Long clieteId, StatusEnum status, TipoPedidoEnum tipo, FormaPagamentoEnum formaPagamento,
                            LocalDate peridoInicio, LocalDate periodoFim) {
}
