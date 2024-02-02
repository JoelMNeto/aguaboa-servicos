package br.com.aguaboaservicos.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aguaboaservicos.cliente.model.ClienteAtualizacao;
import br.com.aguaboaservicos.cliente.model.ClienteCadastro;
import br.com.aguaboaservicos.cliente.model.ClienteFiltros;
import br.com.aguaboaservicos.cliente.model.ClienteInformacoes;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<Page<ClienteInformacoes>> listaClientes(
			@PageableDefault(size = 25, sort = { "saldoEmConta" }, direction = Direction.DESC) Pageable paginacao,
			ClienteFiltros filtros) {
		var pagina = service.listaClientes(paginacao, filtros);

		return ResponseEntity.ok(pagina);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteInformacoes> retornaClientePorId(@PathVariable Long id) {
		var cliente = service.retornaClientePorId(id);

		return ResponseEntity.ok(cliente);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteInformacoes> cadastraCliente(@RequestBody @Valid ClienteCadastro dados,
			UriComponentsBuilder uriBuilder) {
		var cliente = service.cadastraCliente(dados);

		var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.id()).toUri();

		return ResponseEntity.created(uri).body(cliente);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<ClienteInformacoes> atualizaCliente(@RequestBody @Valid ClienteAtualizacao dados) {
		var cliente = service.atualizaCliente(dados);

		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> desativaCliente(@PathVariable Long id) {
		service.desativaCliente(id);

		return ResponseEntity.noContent().build();
	}
}
