package br.com.aguaboaservicos.pedido.validacoes;

import br.com.aguaboaservicos.cliente.ClienteRepository;
import br.com.aguaboaservicos.pedido.model.PedidoLancamento;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoClienteAtivo implements ValidacaoLancamentoPedido {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void validar(PedidoLancamento dadosLancamento) {
        var cliente = clienteRepository.findById(dadosLancamento.clinteId()).orElseThrow(EntityNotFoundException::new);

        if (!cliente.isAtivo()) {
            throw new RuntimeException("Não é possível lançar pedido para um cliente inativo!");
        }
    }
}
