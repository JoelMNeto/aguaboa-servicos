package br.com.aguaboaservicos.pedido.model;

import br.com.aguaboaservicos.cliente.ClienteService;
import br.com.aguaboaservicos.cliente.model.Cliente;
import br.com.aguaboaservicos.common.utils.ListUtils;
import br.com.aguaboaservicos.common.utils.NumberUtils;
import br.com.aguaboaservicos.pedido.enumerations.FormaPagamentoEnum;
import br.com.aguaboaservicos.pedido.enumerations.StatusEnum;
import br.com.aguaboaservicos.pedido.enumerations.TipoPedidoEnum;
import br.com.aguaboaservicos.pedido.itemPedido.ItemPedido;
import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoCadastro;
import br.com.aguaboaservicos.produto.ProdutoService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "valor_pago")
    private BigDecimal valorPago = BigDecimal.ZERO;

    @Column(name = "valor_atualizado")
    private BigDecimal valorAtualizado = BigDecimal.ZERO;

    private BigDecimal frete = BigDecimal.ZERO;

    private StatusEnum status = StatusEnum.EM_ABERTO;

    @Column(name = "tipo_do_pedido")
    private TipoPedidoEnum tipoDoPedido = TipoPedidoEnum.ENTREGA;

    @Column(name = "forma_pagamento")
    private FormaPagamentoEnum formaPagamento = FormaPagamentoEnum.DINHEIRO;

    @Column(name = "data_de_criacao")
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    @ManyToOne()
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<ItemPedido> itens = new ArrayList<>();

    private boolean ativo = true;

    public Pedido(PedidoLancamento dadosLancamento, ClienteService clienteService, ProdutoService produtoService) {
        this.cliente = clienteService.buscaEntidadeCliente(dadosLancamento.clienteId());

        cadastraItensPedido(dadosLancamento.itens(), produtoService);

        if (NumberUtils.isNotEmpty(dadosLancamento.valorPago())) {
            this.valorPago = dadosLancamento.valorPago();
        }

        if (dadosLancamento.tipo() != null) {
            this.tipoDoPedido = dadosLancamento.tipo();
        }

        if (NumberUtils.isNotEmpty(dadosLancamento.frete())) {
            this.frete = dadosLancamento.frete();
        }

        if (dadosLancamento.formaPagamento() != null) {
            this.formaPagamento = dadosLancamento.formaPagamento();
        }

        calculaValoresPedido();

        atualizaValoresPedido();
    }

    public void alteraPedido(PedidoAlteracao dadosAlteracao, ProdutoService produtoService) {
        if (NumberUtils.isNotEmpty(dadosAlteracao.valorPago())) {
            this.valorPago = this.valorPago.add(dadosAlteracao.valorPago());
        }

        if (ListUtils.isNotEmpty(dadosAlteracao.itens())) {
            this.itens.clear();

            cadastraItensPedido(dadosAlteracao.itens(), produtoService);

            calculaValoresPedido();
        }

        atualizaValoresPedido();
    }

    public void cancelaPedido() {
        this.ativo = false;

        this.cliente.somaSaldo(this.valorAtualizado);

        this.itens.forEach(ItemPedido::cancelaItem);
    }

    public List<ItemPedido> getItensPedido() {
        return this.itens;
    }

    private void atualizaValoresPedido() {
        this.valorAtualizado = this.valorTotal.subtract(this.valorPago);

        if (this.valorAtualizado.compareTo(BigDecimal.ZERO) > 0) {
            this.cliente.subtraiSaldo(this.valorAtualizado.abs());

            return;
        }

        BigDecimal valor = this.valorAtualizado.compareTo(BigDecimal.ZERO) == 0 ?
                this.valorTotal.abs() :
                this.valorAtualizado.abs();

        this.cliente.somaSaldo(valor);

        this.status = StatusEnum.PAGO;

        this.valorAtualizado = BigDecimal.ZERO;
    }

    private void calculaValoresPedido() {
        BigDecimal valorPedido =
                this.itens.stream().map(i -> i.getPrecoUnitario().subtract(i.getDesconto()).multiply(i.getQuantidade()))
                        .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        this.valorTotal = valorPedido.add(this.frete);
    }

    private void cadastraItensPedido(List<ItemPedidoCadastro> itens, ProdutoService produtoService) {
        this.itens = itens
                .stream()
                .map(i -> new ItemPedido(this, produtoService, i))
                .toList();
    }
}
