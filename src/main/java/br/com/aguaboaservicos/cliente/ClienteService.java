package br.com.aguaboaservicos.cliente;

import br.com.aguaboaservicos.cliente.filtros.FiltroCliente;
import br.com.aguaboaservicos.cliente.model.*;
import br.com.aguaboaservicos.common.filtro.FiltroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private FiltroService<Cliente, ClienteFiltros> filtroService;

    @Autowired
    private List<FiltroCliente> filtroList;

    public Page<ClienteInformacoes> listaClientes(Pageable paginacao, ClienteFiltros filtros) {
        return repository.findAll(filtroService.adicicionaFiltros(filtros, filtroList), paginacao)
                .map(ClienteInformacoes::new);
    }

    public ClienteInformacoes buscaClientePorId(Long id) {
        return new ClienteInformacoes(repository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Cliente buscaEntidadeCliente(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public ClienteInformacoes cadastraCliente(ClienteCadastro dadosCadastro) {
        var cliente = new Cliente(dadosCadastro);

        repository.save(cliente);

        return new ClienteInformacoes(cliente);
    }

    public ClienteInformacoes alteraCliente(ClienteAlteracao dadosAlteracao) {
        var cliente = repository.findById(dadosAlteracao.id()).orElseThrow(EntityNotFoundException::new);

        cliente.alteraCliente(dadosAlteracao);

        return new ClienteInformacoes(cliente);
    }

    public void desativaCliente(Long id) {
        repository.findById(id).orElseThrow(EntityNotFoundException::new).desativaCliente();
    }
}
