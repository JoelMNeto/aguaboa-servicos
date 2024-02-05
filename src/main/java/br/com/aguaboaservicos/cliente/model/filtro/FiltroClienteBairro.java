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
public class FiltroClienteBairro implements Filtro<Cliente, ClienteFiltros> {

	@Override
	public Predicate adicionaFiltro(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder builder,
			ClienteFiltros filtro) {
		return StringUtils.isEmpty(filtro.bairro()) ? builder.conjunction()
				: builder.equal(root.get("endereco").get("bairro"), filtro.bairro());
	}

}
