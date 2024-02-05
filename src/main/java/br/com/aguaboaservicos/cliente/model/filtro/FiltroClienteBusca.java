package br.com.aguaboaservicos.cliente.model.filtro;

import org.springframework.stereotype.Component;

import br.com.aguaboaservicos.cliente.model.Cliente;
import br.com.aguaboaservicos.cliente.model.ClienteFiltros;
import br.com.aguaboaservicos.filtro.Filtro;
import br.com.aguaboaservicos.utils.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class FiltroClienteBusca implements Filtro<Cliente, ClienteFiltros> {

	@Override
	public Predicate adicionaFiltro(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder builder,
			ClienteFiltros filtro) {
		if (StringUtils.isEmpty(filtro.busca())) {
			return builder.conjunction();
		}
		
		return StringUtils.isOnlyNumbers(filtro.busca()) ? builder.equal(root.get("id"), filtro.busca())
				: builder.or(builder.like(root.get("nome"), "%" + filtro.busca() + "%"),
						builder.like(root.get("endereco").get("logradouro"), "%" + filtro.busca() + "%"));
	}

}
