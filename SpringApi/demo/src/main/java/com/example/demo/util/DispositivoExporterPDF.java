package com.example.demo.util;

import com.example.demo.dto.DispositivoDTO;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class DispositivoExporterPDF {

    private List<DispositivoDTO> listDevices;

    public DispositivoExporterPDF(List<DispositivoDTO> listDevices) {
        this.listDevices = listDevices;
    }

    public void escribirCabezeraTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.ORANGE);
        celda.setPadding(4);

        Font fondo = FontFactory.getFont(FontFactory.HELVETICA);
        fondo.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Nombre", fondo));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Estado", fondo));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Tipo", fondo));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Serie", fondo));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Inventario", fondo));
        tabla.addCell(celda);
    }

    public void escribirDatosTabla(PdfPTable table) {
        for (DispositivoDTO dispositivo : listDevices) {
            table.addCell(dispositivo.getName());
            table.addCell(dispositivo.getStateDevice());
            table.addCell(dispositivo.getTypeDevice());
            table.addCell(dispositivo.getNumberSerie());
            /*table.addCell(dispositivo.getNumberInventory());*/
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();
        Font fondo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fondo.setSize(14);
        fondo.setColor(Color.BLUE);

        Paragraph parrafo = new Paragraph("Lista de Dispositivos", fondo);
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);

        documento.add(parrafo);

        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100f);
        tabla.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        tabla.setSpacingBefore(10);

        escribirCabezeraTabla(tabla);
        escribirDatosTabla(tabla);

        documento.add(tabla);
        documento.close();
    }
}
