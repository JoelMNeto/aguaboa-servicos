package br.com.aguaboaservicos.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aguaboaservicos.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

}
