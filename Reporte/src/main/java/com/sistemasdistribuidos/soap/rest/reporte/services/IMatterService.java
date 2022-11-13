package com.sistemasdistribuidos.soap.rest.reporte.services;

import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals.MatterPDF;
import java.util.List;

public interface IMatterService {

    List<MatterPDF> buildMatterByFourthPDF(int valueMont);

}
