package com.sistemasdistribuidos.soap.rest.reporte.services.implementations;

import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals.ExamPDF;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Exam;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.TeacherMatter;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Teacher;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.general.ExamRepository;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.TeacherMatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.IExamService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService implements IExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    TeacherMatterRepository teacherMatterRepository;

    @Override
    public List<ExamPDF> buildMatterExamsByPeriod(int period) {

        final int MONTH_PERIOD1 = 3;
        final int MONTH_PERIOD2 = 6;
        final int MONTH_PERIOD3 = 9;
        final int MONTH_PERIOD4 = 12;
        final int DAY_SINCE = 1;
        final int DAY_30 = 30;
        final int DAY_31 = 31;

        LocalDate now = LocalDate.now();
        LocalDate since = LocalDate.now();
        LocalDate until = LocalDate.now();

        switch (period){
            case 1:
                since = LocalDate.of(now.getYear(), MONTH_PERIOD1, DAY_SINCE);
                until = LocalDate.of(now.getYear(), MONTH_PERIOD1, DAY_31);
                break;
            case 2:
                since = LocalDate.of(now.getYear(), MONTH_PERIOD2, DAY_SINCE);
                until = LocalDate.of(now.getYear(), MONTH_PERIOD2, DAY_30);
                break;
            case 3:
                since = LocalDate.of(now.getYear(), MONTH_PERIOD3, DAY_SINCE);
                until = LocalDate.of(now.getYear(), MONTH_PERIOD3, DAY_30);
                break;
            case 4:
                since = LocalDate.of(now.getYear(), MONTH_PERIOD4, DAY_SINCE);
                until = LocalDate.of(now.getYear(), MONTH_PERIOD4, DAY_31);
                break;
            default:
                break;
        }

        List<Exam> exams = examRepository.findAllByFechaBetween(since, until);
        List<TeacherMatter> teacherMatters = teacherMatterRepository.findAllByMatter_IdIn(exams.stream()
                .map(e -> e.getMatter().getId())
                .distinct().collect(Collectors.toList()));

        List<ExamPDF> examPDFS = new ArrayList<>();

        for (Exam e : exams){
            ExamPDF exam = new ExamPDF();
            exam.setDate(e.getFecha());
            exam.setTime(e.getHora());
            exam.setMatterName(e.getMatter().getName());
            exam.setPeriod(exam.returnPeriodValue(period));
            exam.setYearCarrer(e.getMatter().getYearCarrer());

            Teacher teacher = teacherMatters.stream()
                    .filter(tm -> tm.getMatter().getId()==e.getMatter().getId())
                    .map(TeacherMatter::getTeacher).findFirst().orElse(new Teacher());

            exam.setTeacherCompleteName(teacher.getName() + " " + teacher.getLastname());

            examPDFS.add(exam);
        }

        return examPDFS;
    }
}
