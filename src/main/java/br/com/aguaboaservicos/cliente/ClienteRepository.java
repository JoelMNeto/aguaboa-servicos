package br.com.aguaboaservicos.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.aguaboaservicos.cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
	
	@EntityGraph(attributePaths = "endereco")
    Page<Cliente> findAll(Specification<Cliente> specification, Pageable pageable);
}