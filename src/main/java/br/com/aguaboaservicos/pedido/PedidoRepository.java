package br.com.aguaboaservicos.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aguaboaservicos.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
