package br.com.aguaboaservicos.pedido.filtros;

import br.com.aguaboaservicos.pedido.model.Pedido;
import br.com.aguaboaservicos.pedido.model.PedidoFiltros;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class FiltroPedidoAtivo implements FiltroPedido {
    @Override
    public Predicate adicionaFiltro(Root<Pedido> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                    PedidoFiltros filtros) {
        return builder.equal(root.get("ativo"), builder.literal(true));
    }
}
