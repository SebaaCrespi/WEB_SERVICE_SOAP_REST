package com.sistemasdistribuidos.soap.rest.reporte.controllers;

import com.lowagie.text.DocumentException;
import com.sistemasdistribuidos.soap.rest.reporte.models.listed.YearCarrer;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Student;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.general.MatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.user.StudentRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.IPDFService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf")
@Api(tags = "PDF Controller",description = "Controller for PDF generation in Report Module")
public class PDFController {

    @Autowired
    IPDFService pdfService;

    @Autowired
    MatterRepository matterRepository;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("setup")
    @ApiOperation(value = "Setup data", response = String.class)
    @ApiResponse(code = 200, message = "Successfully created data")
    public String setUp(){

        Matter matter = new Matter();
        matter.setName("Matemática");
        matter.setFirstFourMonth(true);
        matter.setTimeSince(LocalTime.of(8,30));
        matter.setTimeUntil(LocalTime.of(12,0));
        matter.setYearCarrer(YearCarrer.PRIMERO);
        matter.setTurn("MAÑANA");

        Matter matter2 = new Matter();
        matter2.setName("Matemática 2");
        matter2.setFirstFourMonth(false);
        matter2.setTimeSince(LocalTime.of(8,30));
        matter2.setTimeUntil(LocalTime.of(12,0));
        matter2.setYearCarrer(YearCarrer.PRIMERO);
        matter2.setTurn("MAÑANA");

        matterRepository.save(matter);
        matterRepository.save(matter2);

        Student student = new Student();
        student.setName("Gabriel Alberto");
        student.setLastname("Fernandez");
        student.setDni("42093874");

        studentRepository.save(student);

        return "OK";
    }

    @GetMapping("/matters")
    @ApiOperation(value = "Create PDF with the Matters filtered by first or second fourth months", response = String.class)
    @ApiResponse(code = 200, message = "Successfully created PDF")
    public String generateMattersPDF(HttpServletResponse response,
            @RequestParam("fourth_month")
            @ApiParam(value = "fourth_month", required = true, example = "1") int value){

        try {
            Path file = Paths.get(pdfService.generatePdf(value,"MATTER").getAbsolutePath());

            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }

        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }

        return "Created";
    }

    @GetMapping("/analytic")
    @ApiOperation(value = "Create PDF with Analytic Certificate from the requested Student", response = String.class)
    @ApiResponse(code = 200, message = "Successfully created PDF")
    public String generateAnalyticPDF(HttpServletResponse response,
            @RequestParam("studentId")
            @ApiParam(value = "studentId", required = true, example = "1") long studentId){

        try {
            Path file = Paths.get(pdfService.generatePdf(studentId, "ANALYTIC").getAbsolutePath());

            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }

        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }

        return "Created";
    }

    @GetMapping("/exams")
    @ApiOperation(value = "Create PDF with all Matters filtered by the requested exam period", response = String.class)
    @ApiResponse(code = 200, message = "Successfully created PDF")
    public String generateExamsPDF(HttpServletResponse response,
            @RequestParam("period")
            @ApiParam(value = "period", required = true, example = "1") int period){

        try {
            Path file = Paths.get(pdfService.generatePdf(period).getAbsolutePath());

            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }

        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }

        return "Created";
    }

}
