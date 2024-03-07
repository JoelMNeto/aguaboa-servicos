package br.com.aguaboaservicos.pedido.impressao;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

@Component
public class ImpressaoPedido implements Printable {
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }
}
