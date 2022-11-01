package com.sistemasdistribuidos.soap.rest.reporte.services;

import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals.ExamPDF;
import java.util.List;

public interface IExamService {

    List<ExamPDF> buildMatterExamsByPeriod(int period);

}
