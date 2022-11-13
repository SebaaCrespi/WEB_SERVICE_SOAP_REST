package com.sistemasdistribuidos.soap.rest.reporte.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public interface IExcelService {

    File generateExcelStudentInscriptionMatter(long matterId, LocalDate since, LocalDate until)
            throws IOException;

    File generateExcelStudentInscriptionExam(long matterId, LocalDate since, LocalDate until)
            throws IOException;

}
