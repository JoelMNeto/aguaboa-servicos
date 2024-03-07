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

        double bodyHeight = heigth;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 0, width, height - cm_to_pp(1));

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    private double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    private double toPPI(double inch) {
        return inch * 72d;
    }
}
