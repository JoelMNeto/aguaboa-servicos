package br.com.aguaboaservicos.filtro;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FiltroService<T, K> {

    public <I extends Filtro<T, K>> Specification<T> adicicionaFiltros(K filtros, List<I> filtrosList) {
        return (root, query, builder) -> builder.and(filtrosList.stream()
                .map(f -> f.adicionaFiltro(root, query, builder, filtros))
                .filter(Objects::nonNull)
                .reduce(builder::and)
                .orElseGet(builder::conjunction));
    }
}
