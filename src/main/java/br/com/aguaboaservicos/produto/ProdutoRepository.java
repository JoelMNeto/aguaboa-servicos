package br.com.aguaboaservicos.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aguaboaservicos.produto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
