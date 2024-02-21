package br.com.aguaboaservicos.pedido.filtros;

import br.com.aguaboaservicos.common.utils.StringUtils;
import br.com.aguaboaservicos.pedido.model.Pedido;
import br.com.aguaboaservicos.pedido.model.PedidoFiltros;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class FiltroPedidoBusca implements FiltroPedido {
    @Override
    public Predicate adicionaFiltro(Root<Pedido> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                    PedidoFiltros filtros) {
        if (StringUtils.isEmpty(filtros.busca())) {
            return null;
        }

        if (StringUtils.isOnlyNumbers(filtros.busca())) {
            return builder.equal(root.get("id"), filtros.busca());
        }

        return builder.or(builder.like(builder.upper(root.get("cliente").get("nome")), filtros.busca()),
                builder.like(builder.upper(root.get("produto").get("nome")), filtros.busca()));
    }
}
