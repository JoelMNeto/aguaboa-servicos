package br.com.aguaboaservicos.cliente.filtro;

import org.springframework.stereotype.Component;

import br.com.aguaboaservicos.cliente.model.Cliente;
import br.com.aguaboaservicos.cliente.model.ClienteFiltros;
import br.com.aguaboaservicos.filtro.Filtro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class FiltroClienteAtivo implements Filtro<Cliente, ClienteFiltros> {

	@Override
	public Predicate adicionaFiltro(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder builder,
			ClienteFiltros filtro) {
		return builder.equal(root.get("ativo"), builder.literal(true));
	}

}
