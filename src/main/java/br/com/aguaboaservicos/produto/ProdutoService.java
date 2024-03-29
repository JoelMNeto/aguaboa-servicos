package br.com.aguaboaservicos.produto;

import br.com.aguaboaservicos.common.filtro.FiltroService;
import br.com.aguaboaservicos.produto.filtros.FiltroProduto;
import br.com.aguaboaservicos.produto.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private FiltroService<Produto, ProdutoFiltros> filtrosService;

    @Autowired
    private List<FiltroProduto> filtroList;

    public Page<ProdutoInformacoes> listaProdutos(Pageable paginacao, ProdutoFiltros filtros) {
        return repository.findAll(filtrosService.adicicionaFiltros(filtros, filtroList), paginacao)
                .map(ProdutoInformacoes::new);
    }

    public ProdutoInformacoes buscaProdutoPorId(Long id) {
        return new ProdutoInformacoes(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Produto buscaEntidadeProduto(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public ProdutoInformacoes cadastraProduto(ProdutoCadastro dadosCadastro) {
        var produto = new Produto(dadosCadastro);

        repository.save(produto);

        return new ProdutoInformacoes(produto);
    }

    public ProdutoInformacoes alteraProduto(ProdutoAlteracao dadosAlteracao) {
        var produto = repository.findById(dadosAlteracao.id()).orElseThrow(EntityNotFoundException::new);

        produto.alteraProduto(dadosAlteracao);

        return new ProdutoInformacoes(produto);
    }

    public void desativaProduto(Long id) {
        repository.findById(id).orElseThrow(EntityNotFoundException::new).desativaProduto();
    }
}
