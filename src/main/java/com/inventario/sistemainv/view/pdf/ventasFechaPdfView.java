package com.inventario.sistemainv.view.pdf;

import com.inventario.sistemainv.domain.Ventas;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component("reportes_fecha")
public class ventasFechaPdfView extends AbstractPdfView {

    public static final String IMAGEN = "src/main/resources/static/images/logoPdf.PNG";
    public static BigDecimal total;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        document.addTitle("Reporte Generado por fecha");

        List<Ventas> ventasList = (List<Ventas>) model.get("ventasfecha");
        String fechaInicio = (String) model.get("fechaInicio");
        String fechaFin = (String) model.get("fechaFin");
        PdfPCell cell = null;

        PdfPTable encabezado = new PdfPTable(3);
        encabezado.setWidthPercentage(100);
        encabezado.setSpacingAfter(20);
        encabezado.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
        encabezado.addCell(img(IMAGEN));
        encabezado.addCell(encabezadoText("LA ECUATORIANA S.A."));
        encabezado.addCell(textinfo("Versalles N22-87 y Ramirez Dávalos Esq."));
        encabezado.addCell(textinfo("2229100-2942900"));
        encabezado.addCell(textinfo("Quito-Ecuador"));
        document.add(encabezado);

        PdfPTable title = new PdfPTable(1);
        title.addCell(titleText("Reporte Generado\n " + "del " + fechaInicio + " hasta " + fechaFin));
        title.setSpacingAfter(17);
        document.add(title);

        PdfPTable report = new PdfPTable(1);
        report.setWidthPercentage(29);
        report.setSpacingAfter(17);
        report.setHorizontalAlignment(PdfTable.ALIGN_RIGHT);
        report.addCell(reportText("Reporte Generado"));
        report.addCell(date());
        document.add(report);

        PdfPTable ventasTable = new PdfPTable(6);
        ventasTable.setWidthPercentage(110);
        ventasTable.setWidths(new float[] {1.5f, 3.5f, 1.5f, 1.5f, 1.5f, 1});
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
            ventasTable.addCell(tableBodyProduc(ventas.getProduct_id().getName()));
            ventasTable.addCell(ventasTableBody(ventas.getProduct_id().getBuy_price().toString()));
            ventasTable.addCell(ventasTableBody(ventas.getProduct_id().getSale_price().toString()));
            ventasTable.addCell(ventasTableBody(ventas.getQty().toString()));
            ventasTable.addCell(ventasTableBody(ventas.getPrice().toString()));
            total = total.add(ventas.getPrice());
        }

        cell = new PdfPCell(new Phrase("Total"));
        cell.setColspan(5);
        cell.setPadding(5);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        ventasTable.addCell(cell);
        ventasTable.addCell(ventasTableBody(total.toString()));

        document.add(ventasTable);

    }

    public PdfPCell img(String path) {
        PdfPCell cell = new PdfPCell();
        Image image;
        try {
            image = Image.getInstance(IMAGEN);
            cell.addElement(image);
            cell.setRowspan(4);
            cell.setBorderWidth(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cell;
    }

    public static PdfPCell encabezadoText(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setColspan(2);
        cell.setBorderWidth(0);
        cell.setPhrase(new Phrase(text));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return cell;
    }

    public static PdfPCell textinfo(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setColspan(2);
        cell.setPadding(2);
        cell.setBorderWidth(0);
        cell.setPhrase(new Phrase(text, FontFactory.getFont(FontFactory.TIMES, 9)));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
        return cell;
    }

    public static PdfPCell titleText(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(0);
        cell.setPhrase(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 17)));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setPadding(8);
        return cell;
    }

    public static PdfPCell reportText(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        cell.setBackgroundColor(new Color(167, 162, 162));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setPadding(7);
        return cell;
    }

    public static PdfPCell date() {
        PdfPCell cell = new PdfPCell();
        SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
        String date = fecha.format(new Date());
        cell.setPhrase(new Phrase(date));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setPadding(7);
        return cell;
    }

    public static PdfPCell ventasTableHead(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(56, 135, 199));
        cell.setPhrase(new Phrase(text));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setPadding(8);
        return cell;
    }

    public static PdfPCell ventasTableBody(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setPadding(5);
        return cell;
    }

    public static PdfPCell tableBodyProduc(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(text));
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        cell.setPadding(5);
        return cell;
    }
}
