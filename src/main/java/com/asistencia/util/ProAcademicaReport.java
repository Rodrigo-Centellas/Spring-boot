//package com.asistencia.util;
////package com.example.demoJasperReport.util;
//
//
//import com.asistencia.models.Clase;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.JRXlsExporter;
//import net.sf.jasperreports.export.SimpleExporterInput;
//import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//@Service
//public class ProAcademicaReport {
//
//
//    public byte[] exportToPdf(List<Clase> list) throws JRException, FileNotFoundException {
//        return JasperExportManager.exportReportToPdf(getReport(list));
//    }
//
//    public byte[] exportToXls(List<Clase> list) throws JRException, FileNotFoundException {
//        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
//        JRXlsExporter exporter = new JRXlsExporter();
//        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
//        exporter.setExporterOutput(output);
//        exporter.exportReport();
//        output.close();
//        return byteArray.toByteArray();
//    }
//
//    private JasperPrint getReport(List<Clase> list) throws FileNotFoundException, JRException {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("ClaseData", new JRBeanCollectionDataSource(list));
//
//        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
//                ResourceUtils.getFile("classpath:Programacion_academica_report.jrxml")
//                        .getAbsolutePath()), params, new JREmptyDataSource());
//
//        return report;
//    }
//}


package com.asistencia.util;

import com.asistencia.models.Clase;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProAcademicaReport {

    public byte[] exportToPdf(List<Clase> list) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }

    public byte[] exportToXls(List<Clase> list) throws JRException, FileNotFoundException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getReport(List<Clase> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("ClaseData", new JRBeanCollectionDataSource(list));

        JasperPrint report = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(
                        ResourceUtils.getFile("classpath:Programacion_academica_report.jrxml").getAbsolutePath()
                ),
                params,
                new JREmptyDataSource()
        );

        return report;
    }
}
