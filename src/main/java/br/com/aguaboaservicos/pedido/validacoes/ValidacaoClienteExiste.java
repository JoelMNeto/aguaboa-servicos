package br.com.aguaboaservicos.pedido.validacoes;

import br.com.aguaboaservicos.cliente.ClienteRepository;
import br.com.aguaboaservicos.pedido.model.PedidoLancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoClienteExiste implements ValidacaoLancamentoPedido {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void validar(PedidoLancamento dadosLancamento) {

        if (!clienteRepository.existsById(dadosLancamento.clienteId())) {

            throw new RuntimeException("Não é possível lançar um pedido com um cliente inexistente! " + "Cliente: " +
                    dadosLancamento.clienteId());
        }
    }
}