package br.com.aguaboaservicos.common.impressao;

import org.springframework.stereotype.Service;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

@Service
public class ImpressaoService {

    public void imprimir(Printable printable, Double heigth) {
        PrinterJob pj = PrinterJob.getPrinterJob();

        pj.setPrintable(printable, getPageFormat(pj, heigth));

        try {
            pj.print();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private PageFormat getPageFormat(PrinterJob pj, Double heigth) {
        PageFormat pf = pj.defaultPage();

        Paper paper = pf.getPaper();

        paper.setImageableArea(0, 0, pf.getWidth(), pf.getHeight() + heigth);

        pf.setPaper(paper);

        pf.setOrientation(PageFormat.PORTRAIT);

        pf = pj.validatePage(pf);

        return pf;
    }
}
