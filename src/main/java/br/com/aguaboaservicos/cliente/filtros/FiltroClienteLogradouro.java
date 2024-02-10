package br.com.aguaboaservicos.cliente.filtros;

import br.com.aguaboaservicos.cliente.model.Cliente;
import br.com.aguaboaservicos.cliente.model.ClienteFiltros;
import br.com.aguaboaservicos.utils.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class FiltroClienteLogradouro implements FiltroCliente {

    @Override
    public Predicate adicionaFiltro(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                    ClienteFiltros filtro) {
        if (StringUtils.isEmpty(filtro.logradouro())) {
            return null;
        }

        return builder.equal(root.get("endereco").get("logradouro"), filtro.logradouro());
    }

}
