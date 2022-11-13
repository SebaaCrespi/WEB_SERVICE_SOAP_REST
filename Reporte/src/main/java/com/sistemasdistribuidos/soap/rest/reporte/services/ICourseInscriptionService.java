package com.sistemasdistribuidos.soap.rest.reporte.services;

import com.sistemasdistribuidos.soap.rest.reporte.dto.excel.StudentDTO;
import java.time.LocalDate;
import java.util.List;

public interface ICourseInscriptionService {

    List<StudentDTO> getStudentsByMatterInscriptionExcel(long matterId, LocalDate since, LocalDate until);

}
