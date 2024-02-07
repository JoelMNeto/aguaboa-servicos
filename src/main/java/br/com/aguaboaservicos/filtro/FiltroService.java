package br.com.aguaboaservicos.filtro;

import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiltroService<T, K> {

	@Autowired
	private List<Filtro<T, K>> listFiltros;

	public Specification<T> adicicionaFiltros(K filtros) {
		return (root, query, builder) -> builder.and(listFiltros.stream()
				.map(f -> f.adicionaFiltro(root, query, builder, filtros)).toArray(Predicate[]::new));
	}
}
