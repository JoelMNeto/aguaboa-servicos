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
public class FiltroProdutoBusca implements Filtro<Produto, ProdutoFiltros> {
    
    @Override
    public Predicate adicionaFiltro(Root<Produto> root, CriteriaQuery<?> query, CriteriaBuilder builder, ProdutoFiltros filtro) {
        if (StringUtils.isEmpty(filtro.busca())) {
            return builder.conjunction();
        }
        
        return StringUtils.isOnlyNumbers(filtro.busca()) ? builder.equal(root.get("id"), filtro.busca())
                : builder.or(builder.like(builder.upper(root.get("nome")), "%" + filtro.busca().toUpperCase() + "%"),
                builder.like(builder.upper(root.get("endereco").get("logradouro")), "%" + filtro.busca().toUpperCase() + "%"));
    }
}
