package br.com.aguaboaservicos.produto;

import br.com.aguaboaservicos.produto.model.ProdutoAlteracao;
import br.com.aguaboaservicos.produto.model.ProdutoCadastro;
import br.com.aguaboaservicos.produto.model.ProdutoFiltros;
import br.com.aguaboaservicos.produto.model.ProdutoInformacoes;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoInformacoes>> listaProdutos(
            @PageableDefault(size = 25, sort = {"nome"}) Pageable paginacao, ProdutoFiltros filtros) {
        var pagina = service.listaProdutos(paginacao, filtros);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoInformacoes> buscaProdutoPorId(@PathVariable Long id) {
        var produto = service.buscaProdutoPorId(id);

        return ResponseEntity.ok(produto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoInformacoes> cadastraProduto(@RequestBody @Valid ProdutoCadastro dadosCadastro,
                                                              UriComponentsBuilder uriBuilder) {
        var produto = service.cadastraProduto(dadosCadastro);

        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.id()).toUri();

        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProdutoInformacoes> alteraProduto(@RequestBody @Valid ProdutoAlteracao dadosAlteracao) {
        var produto = service.alteraProduto(dadosAlteracao);

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> desativaProduto(@PathVariable Long id) {
        service.desativaProduto(id);

        return ResponseEntity.noContent().build();
    }
}
