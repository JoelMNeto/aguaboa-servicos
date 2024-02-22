package br.com.aguaboaservicos.pedido.validacoes;

import br.com.aguaboaservicos.common.utils.ListUtils;
import br.com.aguaboaservicos.pedido.model.PedidoLancamento;
import br.com.aguaboaservicos.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoProdutosExistem implements ValidacaoLancamentoPedido {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void validar(PedidoLancamento dadosLancamento) {
        var produtos = dadosLancamento
                .itens()
                .stream()
                .map(i -> !produtoRepository.existsById(i.produtoId()))
                .filter(Boolean::booleanValue)
                .toList();

        if (ListUtils.isNotEmpty(produtos)) {
            throw new RuntimeException("Não é possível lançar um pedido com produtos inexistentes!");
        }
    }
}
