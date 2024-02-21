package br.com.aguaboaservicos.pedido.filtros;

import br.com.aguaboaservicos.pedido.model.Pedido;
import br.com.aguaboaservicos.pedido.model.PedidoFiltros;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class FiltroPedidoPeriodo implements FiltroPedido {
    @Override
    public Predicate adicionaFiltro(Root<Pedido> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                    PedidoFiltros filtros) {
        if (filtros.peridoInicio() == null || filtros.periodoFim() == null) {
            return null;
        }

        return builder.between(root.get("dataDeCriacao"), filtros.peridoInicio(), filtros.periodoFim());
    }
}
