package br.com.aguaboaservicos.cliente.filtros;

import br.com.aguaboaservicos.cliente.model.Cliente;
import br.com.aguaboaservicos.cliente.model.ClienteFiltros;
import br.com.aguaboaservicos.common.utils.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class FiltroClienteBusca implements FiltroCliente {

    @Override
    public Predicate adicionaFiltro(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                    ClienteFiltros filtro) {
        if (StringUtils.isEmpty(filtro.busca())) {
            return null;
        }

        if (StringUtils.isOnlyNumbers(filtro.busca())) {
            return builder.equal(root.get("id"), filtro.busca());
        }

        return builder.or(builder.like(builder.upper(root.get("nome")), "%" + filtro.busca().toUpperCase() + "%"),
                builder.like(builder.upper(root.get("endereco").get("logradouro")),
                        "%" + filtro.busca().toUpperCase() + "%"));
    }

}
