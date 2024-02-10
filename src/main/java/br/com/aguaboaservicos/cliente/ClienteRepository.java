package br.com.aguaboaservicos.cliente;

import br.com.aguaboaservicos.cliente.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

    @Override
    @EntityGraph(attributePaths = "endereco")
    Page<Cliente> findAll(Specification<Cliente> filtros, Pageable paginacao);
}
