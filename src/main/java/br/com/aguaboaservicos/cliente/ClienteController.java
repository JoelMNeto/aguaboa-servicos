package br.com.aguaboaservicos.cliente;

import br.com.aguaboaservicos.cliente.model.ClienteAlteracao;
import br.com.aguaboaservicos.cliente.model.ClienteCadastro;
import br.com.aguaboaservicos.cliente.model.ClienteFiltros;
import br.com.aguaboaservicos.cliente.model.ClienteInformacoes;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Page<ClienteInformacoes>> listaClientes(
            @PageableDefault(size = 25, sort = {"saldoEmConta"}, direction = Direction.ASC) Pageable paginacao,
            ClienteFiltros filtros) {
        var pagina = service.listaClientes(paginacao, filtros);

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteInformacoes> buscaClientePorId(@PathVariable Long id) {
        var cliente = service.buscaClientePorId(id);

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteInformacoes> cadastraCliente(@RequestBody @Valid ClienteCadastro dadosCadastro,
                                                              UriComponentsBuilder uriBuilder) {
        var cliente = service.cadastraCliente(dadosCadastro);

        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.id()).toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ClienteInformacoes> alteraCliente(@RequestBody @Valid ClienteAlteracao dadosAlteracao) {
        var cliente = service.alteraCliente(dadosAlteracao);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> desativaCliente(@PathVariable Long id) {
        service.desativaCliente(id);

        return ResponseEntity.noContent().build();
    }
}
