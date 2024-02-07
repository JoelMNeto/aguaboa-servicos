package br.com.aguaboaservicos.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aguaboaservicos.cliente.model.Cliente;
import br.com.aguaboaservicos.cliente.model.ClienteAlteracao;
import br.com.aguaboaservicos.cliente.model.ClienteCadastro;
import br.com.aguaboaservicos.cliente.model.ClienteFiltros;
import br.com.aguaboaservicos.cliente.model.ClienteInformacoes;
import br.com.aguaboaservicos.filtro.FiltroService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private FiltroService<Cliente, ClienteFiltros> filtroService;

	public Page<ClienteInformacoes> listaClientes(Pageable paginacao, ClienteFiltros filtros) {
		return repository.findAll(filtroService.adicicionaFiltros(filtros), paginacao)
				.map(ClienteInformacoes::new);
	}

	public ClienteInformacoes buscaClientePorId(Long id) {
		return new ClienteInformacoes(repository.findById(id).orElseThrow(EntityNotFoundException::new));
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
