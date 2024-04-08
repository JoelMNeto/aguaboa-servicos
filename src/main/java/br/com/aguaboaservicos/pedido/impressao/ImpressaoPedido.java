package br.com.aguaboaservicos.pedido.impressao;

import br.com.aguaboaservicos.common.utils.StringUtils;
import br.com.aguaboaservicos.pedido.model.PedidoInformacoes;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ImpressaoPedido implements Printable {

    private PedidoInformacoes pedido;

    private Integer cordenadaY = 5;

    private Integer cordenadaX = 10;

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if (pageIndex != 0) {
            return NO_SUCH_PAGE;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        setFont(graphics2D, 12);

        escreveLinha(graphics2D, "------------------------------------------------------", 15);
        escreveLinha(graphics2D, "                ÁGUAS MINERAIS AGUABOA                ", 15);

        setFont(graphics2D, 10);

        escreveLinha(graphics2D, "       Rua Carlos Gomes, 2898 - Araraquara - SP       ", 10);
        escreveLinha(graphics2D, "       (16) 3331-3738                                 ", 10);
        escreveLinha(graphics2D, "------------------------------------------------------", 15);

        setFont(graphics2D, 12);

        escreveLinha(graphics2D, pedido.id().toString(), 10);
        escreveLinha(graphics2D, pedido.data().format(formatter), 15);
        escreveLinha(graphics2D, pedido.tipo().getDescricao(), 15);
        escreveLinha(graphics2D, pedido.cliente().nome(), 10);
        escreveLinha(graphics2D, pedido.cliente().endereco().logradouro() + ", " + pedido.cliente().endereco().numero(),
                15);

        if (StringUtils.isNotEmpty(pedido.cliente().endereco().complemento())) {
            escreveLinha(graphics2D, pedido.cliente().endereco().complemento(), 15);
        }

        if (StringUtils.isNotEmpty(pedido.cliente().endereco().bairro())) {
            escreveLinha(graphics2D, pedido.cliente().endereco().bairro(), 15);
        }

        escreveLinha(graphics2D, "PRODUTO              PREÇO  QUANTDADE  DESCONTO  TOTAL", 10);
        escreveLinha(graphics2D, "------------------------------------------------------", 10);

        pedido.itens().forEach(item -> {
            escreveLinha(graphics2D, item.produto().id() + " - " + item.produto().nome(), 0);

            this.cordenadaX = 30;
            escreveLinha(graphics2D,  "R$ " + item.precoUnitario(), 0);

            this.cordenadaX = 38;
            escreveLinha(graphics2D, item.quantidade().toString(), 0);

            this.cordenadaX = 50;
            escreveLinha(graphics2D, item.desconto().toString(), 0);

            this.cordenadaX = 61;
            escreveLinha(graphics2D, item.precoUnitario().multiply(item.quantidade()).toString(), 0);
        });

        escreveLinha(graphics2D, "------------------------------------------------------", 10);

        return PAGE_EXISTS;
    }

    private void escreveLinha(Graphics2D graphics2D, String conteudo, Integer distanciaProximaLinha) {
        graphics2D.drawString(conteudo, this.cordenadaX, this.cordenadaY);

        this.cordenadaY += distanciaProximaLinha;
    }

    private void setFont(Graphics2D graphics2D, Integer tamanho) {
        graphics2D.setFont(new Font("Arial", Font.BOLD, tamanho));
    }
}
