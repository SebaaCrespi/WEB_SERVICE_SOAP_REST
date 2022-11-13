package com.sistemasdistribuidos.soap.rest.reporte.services.implementations;

import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.AnalyticPDF;
import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals.MatterPDF;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.MatterCourse;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.StudentExam;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.StudentMatter;
import com.sistemasdistribuidos.soap.rest.reporte.models.listed.YearCarrer;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Student;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.MatterCourseRepository;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.StudentExamRepository;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.StudentMatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.IAnalyticService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticService implements IAnalyticService {

    @Autowired
    private StudentMatterRepository studentMatterRepository;

    @Autowired
    private StudentExamRepository studentExamRepository;

    @Autowired
    private MatterCourseRepository matterCourseRepository;

    @Override
    public AnalyticPDF buildAnalyticData(long studentId) {
        AnalyticPDF data = new AnalyticPDF();
        List<MatterPDF> matters = new ArrayList<>();

        List<StudentMatter> studentMatters = studentMatterRepository.findByStudent_Id(studentId);
        List<StudentExam> studentExams = studentExamRepository.findByStudent_Id(studentId);
        List<Long> mattersIds = studentMatters.stream()
                .map(sm -> sm.getMatter().getId()).collect(Collectors.toList());

        Student student = studentMatters.get(0).getStudent();
        data.setDni(student.getDni());
        data.setCompleteName(student.getName() + " " + student.getLastname());

        for (StudentExam sExam : studentExams){
            MatterPDF matterPDF = new MatterPDF();
            Matter matterSE = sExam.getExam().getMatter();
            if (mattersIds.contains(matterSE.getId())){
                StudentMatter studentMatter = studentMatters.stream()
                        .filter(sm -> sm.getMatter().getId() == matterSE.getId())
                        .findFirst().orElse(new StudentMatter());
                float examNote = sExam.getNote();
                float matterNote = (studentMatter.getNoteFirst() + studentMatter.getNoteSecond()) / 2;

                matterPDF.setName(matterSE.getName());
                matterPDF.setNote((matterNote + examNote) / 2);
                matterPDF.setDate(sExam.getExam().getFecha());
                matterPDF.setYear(matterSE.getYearCarrer().toString());

                matters.add(matterPDF);
            }
        }

        data.setMatters1(matters.stream().filter(m -> m.getYear().equals(YearCarrer.PRIMERO.toString()))
                .collect(Collectors.toList()));
        data.setMatters2(matters.stream().filter(m -> m.getYear().equals(YearCarrer.SEGUNDO.toString()))
                .collect(Collectors.toList()));
        data.setMatters3(matters.stream().filter(m -> m.getYear().equals(YearCarrer.TERCERO.toString()))
                .collect(Collectors.toList()));
        data.setMatters4(matters.stream().filter(m -> m.getYear().equals(YearCarrer.CUARTO.toString()))
                .collect(Collectors.toList()));
        data.setAverage(data.calculateAverage());

        return data;
    }
}
