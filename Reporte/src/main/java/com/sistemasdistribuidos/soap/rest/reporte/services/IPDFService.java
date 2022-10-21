package com.sistemasdistribuidos.soap.rest.reporte.services;

import com.lowagie.text.DocumentException;
import java.io.File;
import java.io.IOException;

public interface IPDFService {

    File generatePdf(int valueMont) throws IOException, DocumentException;

}
