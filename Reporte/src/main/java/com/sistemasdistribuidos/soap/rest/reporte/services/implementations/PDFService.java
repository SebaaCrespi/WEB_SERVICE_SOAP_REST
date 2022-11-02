package com.sistemasdistribuidos.soap.rest.reporte.services.implementations;

import com.lowagie.text.DocumentException;
import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.AnalyticPDF;
import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals.ExamPDF;
import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals.MatterPDF;
import com.sistemasdistribuidos.soap.rest.reporte.models.listed.YearCarrer;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.general.MatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.IAnalyticService;
import com.sistemasdistribuidos.soap.rest.reporte.services.IExamService;
import com.sistemasdistribuidos.soap.rest.reporte.services.IMatterService;
import com.sistemasdistribuidos.soap.rest.reporte.services.IPDFService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
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
    private IAnalyticService analyticService;

    @Autowired
    private IMatterService matterService;

    @Autowired
    private IExamService examService;

    @Override
    public File generatePdf(int valueMont, String turn, long courseId, String type) throws IOException, DocumentException {

        List<MatterPDF> matters = matterService.buildMatterByFourthPDF(valueMont, turn, courseId);

        Context context = getContext(matters, type);

        String html = loadAndFillTemplate(context, type);

        return renderPdf(html);
    }

    @Override
    public File generatePdf(long studentId, long courseId, String type) throws IOException, DocumentException {
        Context context = getContext(analyticService.buildAnalyticData(studentId, courseId), type);

        String html = loadAndFillTemplate(context, type);

        return renderPdf(html);
    }

    @Override
    public File generatePdf(int period) throws DocumentException, IOException {

        List<ExamPDF> exams = examService.buildMatterExamsByPeriod(period);

        Context context = getContext(exams, "EXAM");

        String html = loadAndFillTemplate(context, "EXAM");

        return renderPdf(html);
    }

    private Context getContext(Object value, String type) {
        Context context = new Context();

        switch (type){

            case "MATTER":{

                List<MatterPDF> matters = (List<MatterPDF>) value;

                List<MatterPDF> primero = matters.stream().filter(m -> m.getYearCarrer().equals(
                        YearCarrer.PRIMERO)).collect(Collectors.toList());
                List<MatterPDF> segundo = matters.stream().filter(m -> m.getYearCarrer().equals(
                        YearCarrer.SEGUNDO)).collect(Collectors.toList());
                List<MatterPDF> tercero = matters.stream().filter(m -> m.getYearCarrer().equals(
                        YearCarrer.TERCERO)).collect(Collectors.toList());
                List<MatterPDF> cuarto = matters.stream().filter(m -> m.getYearCarrer().equals(
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
                AnalyticPDF analyticPDF = (AnalyticPDF) value;
                String data = String.format("Conste que el alumno %s DNI Nº %s ha aprobado "
                        + "los espacios curriculares que, con las respectivas calificaciones que abajo se registran, "
                        + "correspondientes a la carrera %s",
                        analyticPDF.getCompleteName(), analyticPDF.getDni(), "Licenciatura en Sistemas");
                LocalDate date = LocalDate.now();
                String footer = String.format("A solicitud del interesado y a los efectos que corresponda, "
                                + "se extiende el presente certificado, en la ciudad de Lanús "
                                + "(Bs.As.) a los %d dias del mes %d del año %d",
                date.getDayOfMonth(), date.getMonthValue(), date.getYear());

                context.setVariable("analytic", data);
                context.setVariable("primero", analyticPDF.getMatters1());
                context.setVariable("segundo", analyticPDF.getMatters2());
                context.setVariable("tercero", analyticPDF.getMatters3());
                context.setVariable("cuarto", analyticPDF.getMatters4());
                context.setVariable("average", "PROMEDIO GENERAL: " +
                        String.valueOf(analyticPDF.getAverage()).substring(0,4));
                context.setVariable("footer", footer);
                break;
            }

            case "EXAM":{

                List<ExamPDF> exams = (List<ExamPDF>) value;
                List<ExamPDF> primero = exams.stream().filter(e -> e.getYearCarrer().equals(YearCarrer.PRIMERO))
                        .collect(Collectors.toList());
                List<ExamPDF> segundo = exams.stream().filter(e -> e.getYearCarrer().equals(YearCarrer.SEGUNDO))
                        .collect(Collectors.toList());
                List<ExamPDF> tercero = exams.stream().filter(e -> e.getYearCarrer().equals(YearCarrer.TERCERO))
                        .collect(Collectors.toList());
                List<ExamPDF> cuarto = exams.stream().filter(e -> e.getYearCarrer().equals(YearCarrer.CUARTO))
                        .collect(Collectors.toList());

                context.setVariable("primero", primero);
                context.setVariable("segundo", segundo);
                context.setVariable("tercero", tercero);
                context.setVariable("cuarto", cuarto);
                context.setVariable("period", String.format("MATERIAS DEL %s LLAMADO A EXÁMENES",
                        exams.get(0).getPeriod()));
                break;
            }

            default:
                break;
        }

        return context;
    }

    public String loadAndFillTemplate(Context context, String type) {
        switch (type){
            case "MATTER": return templateEngine.process("matter_template", context);
            case "ANALYTIC": return templateEngine.process("analytic_template", context);
            case "EXAM": return templateEngine.process("exam_template", context);
            default: return "";
        }
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
