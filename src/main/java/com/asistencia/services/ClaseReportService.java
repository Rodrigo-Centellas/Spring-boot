package com.asistencia.services;

import com.asistencia.models.Clase;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface ClaseReportService {
    List<Clase> findAll();
    Clase findById(Long id);
    Clase save(Clase clase);
    void deleteById(Long id);
    byte[] exportPdf() throws JRException, FileNotFoundException;
    byte[] exportXls() throws JRException, FileNotFoundException;
}
