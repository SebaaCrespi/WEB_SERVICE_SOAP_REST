package com.sistemasdistribuidos.soap.rest.reporte.services.implementations;

import com.sistemasdistribuidos.soap.rest.reporte.dto.excel.StudentDTO;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.general.MatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.ICourseInscriptionService;
import com.sistemasdistribuidos.soap.rest.reporte.services.IExamInscriptionService;
import com.sistemasdistribuidos.soap.rest.reporte.services.IExcelService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelService implements IExcelService {

    @Autowired
    MatterRepository matterRepository;

    @Autowired
    ICourseInscriptionService courseInscriptionService;

    @Autowired
    IExamInscriptionService examInscriptionService;

    @Override
    public File generateExcelStudentInscriptionMatter(long matterId, LocalDate since,
            LocalDate until) throws IOException {

        List<StudentDTO> studentDTOS = courseInscriptionService
                .getStudentsByMatterInscriptionExcel(matterId, since, until);

        Matter matter = matterRepository.findById(matterId).orElse(new Matter());

        return generateExcel(studentDTOS, false, matter.getName());
    }

    @Override
    public File generateExcelStudentInscriptionExam(long matterId, LocalDate since,
            LocalDate until) throws IOException {

        List<StudentDTO> studentDTOS = examInscriptionService
                .getStudentsByInscriptionExamExcel(matterId, since, until);

        Matter matter = matterRepository.findById(matterId).orElse(new Matter());

        return generateExcel(studentDTOS, true, matter.getName());
    }

    private File generateExcel(List<StudentDTO> students, boolean isExam, String matterName) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Reporte");
        File file = File.createTempFile((isExam ?
                "ReporteInscripcionExamen":"ReporteInscripcionMateria") + "-", ".xls");

        Row baseRow = sheet.createRow(0);
        baseRow.createCell(0).setCellValue("MATERIA:");
        baseRow.createCell(1).setCellValue(matterName.toUpperCase(Locale.ROOT));
        Row row1 = sheet.createRow(1);

        row1.createCell(0).setCellValue("APELLIDO");
        row1.createCell(1).setCellValue("NOMBRE");
        row1.createCell(2).setCellValue("DNI");
        row1.createCell(3).setCellValue("EMAIL");
        if (isExam){
            row1.createCell(4).setCellValue("FECHA");
            row1.createCell(5).setCellValue("HORA");
            row1.createCell(6).setCellValue("DOCENTE");
        }

        int rowCount = 1;
        for (StudentDTO student : students){
            Row row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(student.getLastname());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getDni());
            row.createCell(3).setCellValue(student.getEmail());
            if (isExam){
                row.createCell(4).setCellValue(student.getExamDate());
                row.createCell(5).setCellValue(student.getExamTime());
                row.createCell(6).setCellValue(student.getCompleteNameTeacher());
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        }

        file.deleteOnExit();

        return file;

    }

}
