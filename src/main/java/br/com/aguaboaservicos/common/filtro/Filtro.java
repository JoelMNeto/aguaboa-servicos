package br.com.aguaboaservicos.common.filtro;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface Filtro<T, K> {
	
	Predicate adicionaFiltro(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder, K filtros);

}
