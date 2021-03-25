package com.inventario.sistemainv.view.pdf;

import com.inventario.sistemainv.domain.Ventas;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component("ventas")
public class ventasPdfView extends AbstractPdfView {

    public static final String IMAGEN = "src/main/resources/static/images/logoPdf.PNG";
    public static BigDecimal total;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Ventas> ventasList = (List<Ventas>) model.get("ventas");
        PdfPCell cell = null;

        PdfPTable encabezado = new PdfPTable(1);
        encabezado.addCell(img(IMAGEN));
        encabezado.setSpacingAfter(25);
        document.add(encabezado);

        PdfPTable title = new PdfPTable(1);
        title.addCell(titleText("Reporte General de Ventas"));
        title.setSpacingAfter(25);
        document.add(title);

        PdfPTable report = new PdfPTable(1);
        report.setWidthPercentage(32);
        report.setSpacingAfter(25);
        report.setHorizontalAlignment(PdfTable.ALIGN_RIGHT);
        report.addCell(reportText("Reporte Generado"));
        report.addCell("fecha");
        document.add(report);

        PdfPTable ventasTable = new PdfPTable(6);
        ventasTable.setWidthPercentage(110);
        ventasTable.setSpacingAfter(25);
        ventasTable.addCell(ventasTableHead("Fecha"));
        ventasTable.addCell(ventasTableHead("Producto"));
        ventasTable.addCell(ventasTableHead("Precio de compra"));
        ventasTable.addCell(ventasTableHead("Precio por unidad"));
        ventasTable.addCell(ventasTableHead("Cantidad"));
        ventasTable.addCell(ventasTableHead("Total"));

        total = BigDecimal.ZERO;
        for (Ventas ventas: ventasList) {
            ventasTable.addCell(ventasTableBody(ventas.getDate()));
            ventasTable.addCell(ventasTableBody(ventas.getProduct_id().getName()));
            ventasTable.addCell(ventasTableBody(ventas.getProduct_id().getBuy_price().toString()));
            ventasTable.addCell(ventasTableBody(ventas.getProduct_id().getSale_price().toString()));
            ventasTable.addCell(ventasTableBody(ventas.getQty().toString()));
            ventasTable.addCell(ventasTableBody(ventas.getPrice().toString()));
            total = total.add(ventas.getPrice());
        }

        cell = new PdfPCell(new Phrase("Total"));
        cell.setColspan(5);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        ventasTable.addCell(cell);
        ventasTable.addCell(total.toString());

        document.add(ventasTable);



    }

    public PdfPCell img(String path) {
        PdfPCell cell = new PdfPCell();
        return cell;
    }

    public static PdfPCell titleText(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return cell;
    }

    public static PdfPCell reportText(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        return cell;
    }

    public static PdfPCell ventasTableHead(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return cell;
    }

    public static PdfPCell ventasTableBody(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return cell;
    }
}
