package com.sistemasdistribuidos.soap.rest.reporte.services;

import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.AnalyticPDF;

public interface IAnalyticService {

   AnalyticPDF buildAnalyticData(long studentId);

}
