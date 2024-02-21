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
public class FiltroClienteNome implements FiltroCliente {

    @Override
    public Predicate adicionaFiltro(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                    ClienteFiltros filtros) {
        if (StringUtils.isEmpty(filtros.nome())) {
            return null;
        }

        return builder.equal(root.get("nome"), filtros.nome());
    }

}
