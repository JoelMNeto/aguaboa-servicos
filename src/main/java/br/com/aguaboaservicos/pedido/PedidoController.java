package br.com.aguaboaservicos.pedido;

import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoInformacoes;
import br.com.aguaboaservicos.pedido.model.PedidoAlteracao;
import br.com.aguaboaservicos.pedido.model.PedidoFiltros;
import br.com.aguaboaservicos.pedido.model.PedidoInformacoes;
import br.com.aguaboaservicos.pedido.model.PedidoLancamento;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<Page<PedidoInformacoes>> listaPedidos(@PageableDefault(size = 25, sort = {"status"})
                                                                Pageable paginacao, PedidoFiltros filtros) {
        var pagina = service.listaPedidos(paginacao, filtros);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoInformacoes> buscaPedidoPorId(@PathVariable Long id) {
        var pedido = service.buscaPedidoPorId(id);

        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<Page<ItemPedidoInformacoes>> buscaPedidoPorId(@PageableDefault(size = 25)
                                                                 Pageable paginacao, @PathVariable Long id) {
        var itens = service.buscaItensPedido(id, paginacao);

        return ResponseEntity.ok(itens);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoInformacoes> lancaPedido(@RequestBody @Valid PedidoLancamento dadosLancamento,
                                                         UriComponentsBuilder uriBuilder) {
        var pedido = service.lancaPedido(dadosLancamento);

        var uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.id()).toUri();

        return ResponseEntity.created(uri).body(pedido);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PedidoInformacoes> alteraPedido(@RequestBody @Valid PedidoAlteracao dadosAlteracao) {
        var pedido = service.alteraPedido(dadosAlteracao);

        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> cancelaPedido(@PathVariable Long id) {
        service.cancelaPedido(id);

        return ResponseEntity.noContent().build();
    }
}
