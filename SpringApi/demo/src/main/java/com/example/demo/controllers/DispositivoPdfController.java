package com.example.demo.controllers;

import com.example.demo.dto.DispositivoDTO;
import com.example.demo.services.DispositivoService;
import com.example.demo.util.DispositivoExporterPDF;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class DispositivoPdfController {

    @Autowired
    DispositivoService dispositivoService;

    @GetMapping("/exportar/pdf")
    public void exportarAPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        String llaveCabecera = "Content-Disposition";
        String llaveValor = "attachment; filename=Dispositivos" + ".pdf";
        response.setHeader(llaveCabecera, llaveValor);

        List<DispositivoDTO> dispositivos = dispositivoService.obtenerDispositivos();

        DispositivoExporterPDF exporterPDF = new DispositivoExporterPDF(dispositivos);
        exporterPDF.export(response);
    }

}
