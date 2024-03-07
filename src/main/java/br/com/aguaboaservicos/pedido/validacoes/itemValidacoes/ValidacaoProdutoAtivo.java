package br.com.aguaboaservicos.pedido.validacoes.itemValidacoes;

import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoCadastro;
import br.com.aguaboaservicos.produto.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoProdutoAtivo implements ValidacaoItemPedido {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void validar(ItemPedidoCadastro item) {
        var produto = produtoRepository.findById(item.produtoId()).orElseThrow(EntityNotFoundException::new);

        if (!produto.isAtivo()) {

            throw new RuntimeException(
                    "Não é possível lançar pedido para um produto inativo! " + "Produto: " + produto.getId() + " - " +
                            produto.getNome());
        }
    }
}
