package com.sistemasdistribuidos.soap.rest.reporte.controllers;

import com.lowagie.text.DocumentException;
import com.sistemasdistribuidos.soap.rest.reporte.models.listed.YearCarrer;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.MatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.IPDFService;
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
public class PDFController {

    @Autowired
    IPDFService pdfService;

    @Autowired
    MatterRepository matterRepository;

    @PostMapping("setup")
    public String setUp(){

        Matter matter = new Matter();
        matter.setName("Matemática");
        matter.setFirstFourMonth(true);
        matter.setTimeSince(LocalTime.of(8,30));
        matter.setTimeUntil(LocalTime.of(12,0));
        matter.setYearCarrer(YearCarrer.PRIMERO);

        Matter matter2 = new Matter();
        matter2.setName("Matemática 2");
        matter2.setFirstFourMonth(false);
        matter2.setTimeSince(LocalTime.of(8,30));
        matter2.setTimeUntil(LocalTime.of(12,0));
        matter2.setYearCarrer(YearCarrer.PRIMERO);

        matterRepository.save(matter);
        matterRepository.save(matter2);

        return "OK";
    }

    @GetMapping("/matters")
    public String generateMattersPDF(HttpServletResponse response,
            @RequestParam("fourth_month") int value){

        try {
            Path file = Paths.get(pdfService.generatePdf(value).getAbsolutePath());

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
