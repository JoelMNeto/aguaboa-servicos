package br.com.aguaboaservicos.pedido.validacoes.itemValidacoes;

import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoCadastro;
import br.com.aguaboaservicos.produto.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoItemPreco implements ValidacaoItemPedido {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void validar(ItemPedidoCadastro item) {
        var produto = produtoRepository.findById(item.produtoId()).orElseThrow(EntityNotFoundException::new);

        if (produto.getPreco().compareTo(item.precoUnitario()) < 0) {
            throw new RuntimeException(
                    "O preço do item não pode ser menor do que o preço do produto! " + "Produto: " + produto.getId() +
                            " - " + produto.getNome());
        }
    }
}
