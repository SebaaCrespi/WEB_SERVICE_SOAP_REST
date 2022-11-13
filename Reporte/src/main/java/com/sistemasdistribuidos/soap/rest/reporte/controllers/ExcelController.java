package com.sistemasdistribuidos.soap.rest.reporte.controllers;

import com.sistemasdistribuidos.soap.rest.reporte.services.IExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/excel")
@Api(tags = "EXCEL Controller",description = "Controller for EXCEL generation in Report Module")
public class ExcelController {

    @Autowired
    IExcelService excelService;

    private static final String CONTENT_TYPE = "application/vnd.ms-excel";

    private static final String FIRST_HEADER = "Content-Disposition";

    private static final String SECOND_HEADER = "attachment; filename=";

    @PostMapping("/course")
    @ApiOperation(value = "Create EXCEL with the Students enrolled in a Subject", response = String.class)
    @ApiResponse(code = 200, message = "Successfully created EXCEL")
    public String getExcelInscriptionMatter(
            @ApiParam(value = "matterId", required = true, example = "1")
            @RequestParam(name = "matterId") long matterId,
            @ApiParam(value = "inscriptionDateSince")
            @RequestParam(name = "inscriptionDateSince", required = false) LocalDate since,
            @ApiParam(value = "inscriptionDateUntil")
            @RequestParam(name = "inscriptionDateUntil", required = false)LocalDate until,
            HttpServletResponse response) {

        try {
            Path path = Paths
                    .get(excelService.generateExcelStudentInscriptionMatter(matterId, since, until)
                            .getAbsolutePath());
            if (Files.exists(path)) {
                response.setContentType(CONTENT_TYPE);
                response.addHeader(FIRST_HEADER, SECOND_HEADER + path.getFileName());
                Files.copy(path, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    @PostMapping("/exam")
    @ApiOperation(value = "Create EXCEL with the Students enrolled in a Final Exam", response = String.class)
    @ApiResponse(code = 200, message = "Successfully created EXCEL")
    public String getExcelInscriptionExam(
            @ApiParam(value = "matterId", required = true, example = "1")
            @RequestParam(name = "matterId") long matterId,
            @ApiParam(value = "inscriptionDateSince")
            @RequestParam(name = "inscriptionDateSince", required = false) LocalDate since,
            @ApiParam(value = "inscriptionDateUntil")
            @RequestParam(name = "inscriptionDateUntil", required = false)LocalDate until,
            HttpServletResponse response) {

        try {
            Path path = Paths
                    .get(excelService.generateExcelStudentInscriptionExam(matterId, since, until)
                            .getAbsolutePath());
            if (Files.exists(path)) {
                response.setContentType(CONTENT_TYPE);
                response.addHeader(FIRST_HEADER, SECOND_HEADER + path.getFileName());
                Files.copy(path, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return "";
    }

}
