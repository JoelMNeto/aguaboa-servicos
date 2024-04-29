package br.com.aguaboaservicos.common.impressao;

import org.springframework.stereotype.Service;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

@Service
public class ImpressaoService {

    public void imprimir(Printable printable) {
        PrinterJob pj = PrinterJob.getPrinterJob();

        pj.setPrintable(printable, getPageFormat(pj));

        try {
            pj.print();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();

        Paper paper = pf.getPaper();

        paper.setImageableArea(0, 0, pf.getWidth(), pf.getHeight());

        pf.setPaper(paper);

        pf = pj.validatePage(pf);

        return pf;
    }
}
