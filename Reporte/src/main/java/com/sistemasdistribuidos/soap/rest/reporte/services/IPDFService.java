package com.sistemasdistribuidos.soap.rest.reporte.services;

import com.lowagie.text.DocumentException;
import java.io.File;
import java.io.IOException;

public interface IPDFService {

    File generatePdf(int valueMont, String turn, long courseId, String type) throws IOException, DocumentException;

    File generatePdf(long studentId, long courseId, String type) throws IOException, DocumentException;

    File generatePdf(int period) throws DocumentException, IOException;

}
