package br.com.aguaboaservicos.pedido;

import br.com.aguaboaservicos.cliente.ClienteService;
import br.com.aguaboaservicos.common.filtro.FiltroService;
import br.com.aguaboaservicos.common.impressao.ImpressaoService;
import br.com.aguaboaservicos.pedido.enumerations.TipoPedidoEnum;
import br.com.aguaboaservicos.pedido.filtros.FiltroPedido;
import br.com.aguaboaservicos.pedido.impressao.ImpressaoPedido;
import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoInformacoes;
import br.com.aguaboaservicos.pedido.model.*;
import br.com.aguaboaservicos.pedido.validacoes.ValidacaoLancamentoPedido;
import br.com.aguaboaservicos.pedido.validacoes.itemValidacoes.ValidacaoItemPedido;
import br.com.aguaboaservicos.produto.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    FiltroService<Pedido, PedidoFiltros> filtroService;

    @Autowired
    List<FiltroPedido> filtroList;

    @Autowired
    List<ValidacaoLancamentoPedido> validacoes;

    @Autowired
    List<ValidacaoItemPedido> validacoesItens;

    @Autowired
    ImpressaoService impressaoService;

    public Page<PedidoInformacoes> listaPedidos(Pageable paginacao, PedidoFiltros filtros) {
        return repository.findAll(filtroService.adicicionaFiltros(filtros, filtroList), paginacao)
                .map(PedidoInformacoes::new);
    }

    public PedidoInformacoes buscaPedidoPorId(Long id) {
        return new PedidoInformacoes(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Page<ItemPedidoInformacoes> buscaItensPedido(Long id, Pageable paginacao) {
        List<ItemPedidoInformacoes> list =
                repository.findById(id).orElseThrow(EntityNotFoundException::new).getItens().stream()
                        .map(ItemPedidoInformacoes::new).toList();

        int inicio = (int) paginacao.getOffset();
        int fim = Math.min((inicio + paginacao.getPageSize()), list.size());

        return new PageImpl<>(list.subList(inicio, fim), paginacao, list.size());
    }

    public PedidoInformacoes lancaPedido(PedidoLancamento dadosLancamento) {
        validacoes.forEach(v -> v.validar(dadosLancamento));

        dadosLancamento.itens().forEach(i -> validacoesItens.forEach(v -> v.validar(i)));

        var pedido = new Pedido(dadosLancamento, clienteService, produtoService);

        repository.save(pedido);

        PedidoInformacoes pedidoInformacoes = new PedidoInformacoes(pedido);

        if (TipoPedidoEnum.ENTREGA.equals(pedidoInformacoes.tipo())) {
            impressaoService.imprimir(new ImpressaoPedido(pedidoInformacoes));
        }

        return pedidoInformacoes;
    }

    public PedidoInformacoes alteraPedido(PedidoAlteracao dadosAlteracao) {
        var pedido = repository.findById(dadosAlteracao.id()).orElseThrow(EntityNotFoundException::new);

        pedido.alteraPedido(dadosAlteracao, produtoService);

        return new PedidoInformacoes(pedido);
    }

    public void cancelaPedido(Long id) {
        repository.findById(id).orElseThrow(EntityNotFoundException::new).cancelaPedido();
    }
}
