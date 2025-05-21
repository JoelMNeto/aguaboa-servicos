package br.com.aguaboaservicos.pedido.impressao;

import br.com.aguaboaservicos.common.utils.StringUtils;
import br.com.aguaboaservicos.pedido.itemPedido.ItemPedidoInformacoes;
import br.com.aguaboaservicos.pedido.model.PedidoInformacoes;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ImpressaoPedido implements Printable {

    private PedidoInformacoes pedido;

    private FontMetrics fontMetrics;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");

    private Integer cordenadaY;

    public ImpressaoPedido (PedidoInformacoes pedido) {
        this.pedido = pedido;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if (pageIndex != 0) {
            return NO_SUCH_PAGE;
        }

        this.cordenadaY = 4;
        
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

        insereCabecalho(graphics2D);

        insereInformacoesPedido(graphics2D);

        insereValores(graphics2D);

        return PAGE_EXISTS;
    }

    private void insereValores(Graphics2D graphics2D) {
        escreveLinha(graphics2D, "Produto", 0);
        escreveLinhaDireita(graphics2D, "Preço  Qtde.  Desc.  Total", 5);

        escreveLinha(graphics2D, "-----------------------------------------------------------------------------", 15);

        this.pedido.itens().forEach(item -> insereValoresProdutos(graphics2D, item));

        this.cordenadaY += 10;

        escreveLinha(graphics2D, "-----------------------------------------------------------------------------", 15);

        escreveLinha(graphics2D, "Frete: ", 0);
        escreveLinha(graphics2D, "R$ " + this.pedido.frete(), 10, 200 - this.fontMetrics.stringWidth("R$ 000,00"));

        escreveLinha(graphics2D, "Total do pedido: ", 0);
        escreveLinha(graphics2D, "R$ " + this.pedido.valorTotal(), 10,
                200 - this.fontMetrics.stringWidth("R$ 000,00"));

        if (this.pedido.valorPago().compareTo(BigDecimal.ZERO) > 0) {
            escreveLinha(graphics2D, "Valor pago: ", 0);
            escreveLinha(graphics2D, "R$ " + this.pedido.valorPago(), 10,
                    200 - this.fontMetrics.stringWidth("R$ 000,00"));
        }

        escreveLinha(graphics2D, "Saldo do cliente: ", 0);
        escreveLinha(graphics2D, "R$ " + this.pedido.cliente().saldoEmConta(), 10,
                200 - this.fontMetrics.stringWidth("R$ 000,00"));
    }

    private void insereValoresProdutos(Graphics2D graphics2D, ItemPedidoInformacoes item) {
        escreveLinha(graphics2D, item.produto().nome() + " " + this.getMarca(item), 0);

        escreveLinha(graphics2D, item.precoUnitario().toString(), 0,
                200 - this.fontMetrics.stringWidth("Preço  Qtde.  Desc.  Total"));

        escreveLinha(graphics2D, item.quantidade().toString(), 0,
                200 - this.fontMetrics.stringWidth("Qtde.  Desc.  Total"));

        escreveLinha(graphics2D, item.desconto().toString(), 0, 200 - this.fontMetrics.stringWidth("Desc.  Total"));

        escreveLinha(graphics2D,
                item.quantidade().multiply(item.precoUnitario()).subtract(item.desconto()).toString(), 10,
                200 - this.fontMetrics.stringWidth("Total"));
    }

    private String getMarca(ItemPedidoInformacoes item) {
        String marca = item.produto().marca();

        if (marca == null || marca.isBlank()) {
            return "";
        }

        return marca;
    }

    private void insereInformacoesPedido(Graphics2D graphics2D) {
        escreveLinha(graphics2D, "Pedido: ", 0);
        escreveLinhaDireita(graphics2D, this.pedido.id() + " - " + this.pedido.data().format(this.formatter), 10);

        escreveLinha(graphics2D, "Cliente: ", 0);
        escreveLinhaDireita(graphics2D, this.pedido.cliente().id() + " - " + this.pedido.cliente().nome(), 10);

        escreveLinha(graphics2D, "Endereço: ", 0);
        escreveLinhaDireita(
                graphics2D,
                this.pedido.cliente().endereco().logradouro() + ", " + this.pedido.cliente().endereco().numero(), 10);

        if (StringUtils.isNotEmpty(this.pedido.cliente().endereco().bairro())) {
            escreveLinha(graphics2D, "Bairro: ", 0);
            escreveLinhaDireita(graphics2D, this.pedido.cliente().endereco().bairro(), 10);
        }

        if (StringUtils.isNotEmpty(this.pedido.cliente().endereco().complemento())) {
            escreveLinha(graphics2D, "Complemento: ", 0);
            escreveLinhaDireita(graphics2D, this.pedido.cliente().endereco().complemento(), 10);
        }

        escreveLinha(graphics2D, "Contato: ", 0);
        escreveLinhaDireita(graphics2D, this.pedido.cliente().contato(), 20);
    }

    private void insereCabecalho(Graphics2D graphics2D) {
        setFont(graphics2D, 10);

        escreveLinha(graphics2D, "------------------------------------------------------------", 15);

        escreveLinhaCentralizada(graphics2D, "ÁGUAS MINERAIS AGUABOA", 15);

        setFont(graphics2D, 8);

        escreveLinhaCentralizada(graphics2D, "Rua Carlos Gomes, 2898 - Araraquara - SP", 15);

        setFont(graphics2D, 10);

        escreveLinhaCentralizada(graphics2D, "3331-3738 / 99794-3738", 15);

        escreveLinha(graphics2D, "------------------------------------------------------------", 15);

        setFont(graphics2D, 8);
    }

    private void escreveLinha(Graphics2D graphics2D, String conteudo, Integer distanciaProximaLinha) {
        escreve(graphics2D, conteudo, 0, distanciaProximaLinha);
    }

    private void escreveLinha(Graphics2D graphics2D, String conteudo, Integer distanciaProximaLinha, Integer posicaoX) {
        escreve(graphics2D, conteudo, posicaoX, distanciaProximaLinha);
    }

    private void escreveLinhaCentralizada(Graphics2D graphics2D, String conteudo, Integer distanciaProximaLinha) {
        int x = 100 - (this.fontMetrics.stringWidth(conteudo) / 2);

        escreve(graphics2D, conteudo, x, distanciaProximaLinha);
    }

    private void escreveLinhaDireita(Graphics2D graphics2D, String conteudo, Integer distanciaProximaLinha) {
        int x = 200 - this.fontMetrics.stringWidth(conteudo);

        escreve(graphics2D, conteudo, x, distanciaProximaLinha);
    }

    private void escreve(Graphics2D graphics2D, String conteudo, int x, Integer distanciaProximaLinha) {
        if (conteudo == null) {
            return;
        }

        graphics2D.drawString(conteudo, x, this.cordenadaY);

        this.cordenadaY += distanciaProximaLinha;
    }

    private void setFont(Graphics2D graphics2D, Integer tamanho) {
        Font font = new Font("Monospace", Font.BOLD, tamanho);

        graphics2D.setFont(font);
        this.fontMetrics = graphics2D.getFontMetrics(font);
    }
}
