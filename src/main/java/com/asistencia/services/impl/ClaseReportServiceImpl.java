package com.asistencia.services.impl;

import com.asistencia.models.Clase;
import com.asistencia.repositories.ClaseReportRepositoty;
import com.asistencia.services.ClaseReportService;
import com.asistencia.util.ProAcademicaReport;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
@Service
public class ClaseReportServiceImpl implements ClaseReportService {
    @Autowired
    private ClaseReportRepositoty claseReportRepositoty;

    @Autowired
    private ProAcademicaReport proAcademicaReport;

    @Override
    public List<Clase> findAll() {
        return claseReportRepositoty.findAll();
    }

    @Override
    public Clase findById(Long id) {
        return claseReportRepositoty.findById(id).get();
    }

    @Override
    public Clase save(Clase pet) {
        return claseReportRepositoty.save(pet);
    }

    @Override
    public void deleteById(Long id) {
        claseReportRepositoty.deleteById(id);
    }



    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return proAcademicaReport.exportToPdf(claseReportRepositoty.findAll());
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        return proAcademicaReport.exportToXls(claseReportRepositoty.findAll());
    }
}
