package br.com.aguaboaservicos.produto.filtros;

import br.com.aguaboaservicos.filtro.Filtro;
import br.com.aguaboaservicos.produto.model.Produto;
import br.com.aguaboaservicos.produto.model.ProdutoFiltros;
import br.com.aguaboaservicos.utils.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class FiltroProdutoMarca implements Filtro<Produto, ProdutoFiltros> {
    @Override
    public Predicate adicionaFiltro(Root<Produto> root, CriteriaQuery<?> query, CriteriaBuilder builder, ProdutoFiltros filtro) {
        return StringUtils.isEmpty(filtro.marca()) ? builder.conjunction()
                : builder.like(builder.upper(root.get("marca")), "%" + filtro.marca().toUpperCase() + "%");
    }
}
