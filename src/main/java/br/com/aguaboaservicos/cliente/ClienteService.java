package br.com.aguaboaservicos.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aguaboaservicos.cliente.model.Cliente;
import br.com.aguaboaservicos.cliente.model.ClienteAtualizacao;
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

	public ClienteInformacoes retornaClientePorId(Long id) {
		return new ClienteInformacoes(repository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
	}

	public ClienteInformacoes cadastraCliente(ClienteCadastro dados) {
		var cliente = new Cliente(dados);

		repository.save(cliente);

		return new ClienteInformacoes(cliente);
	}

	public ClienteInformacoes atualizaCliente(ClienteAtualizacao dados) {
		var cliente = repository.findById(dados.id()).orElseThrow(() -> new EntityNotFoundException());

		cliente.atualizaCliente(dados);

		return new ClienteInformacoes(cliente);
	}

	public void desativaCliente(Long id) {
		repository.findById(id).orElseThrow(() -> new EntityNotFoundException()).desativaCliente();
	}
}
