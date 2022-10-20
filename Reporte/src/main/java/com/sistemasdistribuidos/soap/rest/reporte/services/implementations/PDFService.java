package com.sistemasdistribuidos.soap.rest.reporte.services.implementations;

import com.lowagie.text.DocumentException;
import com.sistemasdistribuidos.soap.rest.reporte.dtos.MatterDTO;
import com.sistemasdistribuidos.soap.rest.reporte.listed.YearCarrer;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.MatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.IPDFService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
public class PDFService implements IPDFService {

    private static final String PDF_RESOURCES = "/pdf-resources/";

    @Autowired
    private ISpringTemplateEngine templateEngine;

    @Autowired
    MatterRepository matterRepository;

    @Override
    public File generatePdf(int valueMont) throws IOException, DocumentException {

        List<Matter> matters = matterRepository.getAllByIsFirstFourMonth(valueMont == 1);

        Context context = getContext(matters, "MATTER");

        String html = loadAndFillTemplate(context);

        return renderPdf(html);
    }

    private Context getContext(Object value, String type) {
        Context context = new Context();

        switch (type){
            case "MATTER":{

                List<Matter> matters = (List<Matter>) value;

                List<Matter> primero = matters.stream().filter(m -> m.getYearCarrer().equals(
                        YearCarrer.PRIMERO)).collect(Collectors.toList());
                List<Matter> segundo = matters.stream().filter(m -> m.getYearCarrer().equals(
                        YearCarrer.SEGUNDO)).collect(Collectors.toList());
                List<Matter> tercero = matters.stream().filter(m -> m.getYearCarrer().equals(
                        YearCarrer.TERCERO)).collect(Collectors.toList());
                List<Matter> cuarto = matters.stream().filter(m -> m.getYearCarrer().equals(
                        YearCarrer.CUARTO)).collect(Collectors.toList());

                context.setVariable("month", String.format("MATERIAS %s CUATRIMESTRE",
                        primero.get(0).isFirstFourMonth() ? "PRIMER" : "SEGUNDO"));
                context.setVariable("primero", primero);
                context.setVariable("segundo", segundo);
                context.setVariable("tercero", tercero);
                context.setVariable("cuarto", cuarto);
                break;
            }
            case "ANALYTIC":{
                //TODO: REFORMAR PARA FUTURAS IMPL
                context.setVariable("analytic", value);
                break;
            }
            default:
                break;
        }

        return context;
    }

    public String loadAndFillTemplate(Context context) {
        return templateEngine.process("pdf_template", context);
    }

    private File renderPdf(String html) throws IOException, DocumentException {

        long random = new Random().nextLong();

        File file = File.createTempFile(random + "-" , ".pdf");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
            renderer.setDocumentFromString(html,
                    new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
            renderer.layout();
            renderer.createPDF(outputStream);
        }
        file.deleteOnExit();
        return file;
    }

}
