package com.asistencia.controllers;

import com.asistencia.services.ClaseReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/reporte")
public class ClaseReportController {

    @Autowired
    private ClaseReportService claseReportService;

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "claseReport.pdf");
        return ResponseEntity.ok().headers(headers).body(claseReportService.exportPdf());
    }


    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("ReporteClase" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(claseReportService.exportXls());
    }
}
