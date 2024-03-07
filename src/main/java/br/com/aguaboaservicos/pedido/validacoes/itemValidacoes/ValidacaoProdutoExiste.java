package br.com.aguaboaservicos.pedido.validacoes.itemValidacoes;

import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoCadastro;
import br.com.aguaboaservicos.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoProdutoExiste implements ValidacaoItemPedido {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void validar(ItemPedidoCadastro item) {
        if (!produtoRepository.existsById(item.produtoId())) {

            throw new RuntimeException("Não é possível lançar um pedido com um produto inexistente! " + "Produto: " +
                    item.produtoId());
        }
    }
}
