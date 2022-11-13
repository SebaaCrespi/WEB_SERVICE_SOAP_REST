package com.sistemasdistribuidos.soap.rest.reporte.services.implementations;

import com.sistemasdistribuidos.soap.rest.reporte.dto.excel.StudentDTO;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Exam;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.InscriptionCourse;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.InscriptionExam;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.StudentExam;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.TeacherMatter;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Student;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Teacher;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.InscriptionExamRepository;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.StudentExamRepository;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.TeacherMatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.IExamInscriptionService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamInscriptionService implements IExamInscriptionService {

    @Autowired
    InscriptionExamRepository inscriptionExamRepository;

    @Autowired
    StudentExamRepository studentExamRepository;

    @Autowired
    TeacherMatterRepository teacherMatterRepository;

    @Override
    public List<StudentDTO> getStudentsByInscriptionExamExcel(long matterId, LocalDate since,
            LocalDate until) {

        since = since == null ? LocalDate.of(LocalDate.now().getYear(), 1,1) : since;
        until = until == null ? LocalDate.of(LocalDate.now().getYear(), 12,31) : until;

        LocalDate finalSince = since;
        LocalDate finalUntil = until;
        List<Exam> examList = inscriptionExamRepository.findAll().stream()
                .filter(ie -> ie.getInscription().getTimeSince().isAfter(finalSince) &&
                        ie.getInscription().getTimeUtil().isBefore(finalUntil) &&
                        ie.getExam().getMatter().getId() == matterId)
                .map(InscriptionExam::getExam)
                .collect(Collectors.toList());

        List<StudentExam> studentExams = studentExamRepository.findAllByExam_IdIn(examList.stream()
                .map(Exam::getId).collect(Collectors.toList()));

        Teacher teacher = teacherMatterRepository.findAllByMatter_IdIn(List.of(matterId)).stream().map(
                TeacherMatter::getTeacher).findFirst().orElse(new Teacher());

        List<StudentDTO> result = new ArrayList<>();
        for (StudentExam se : studentExams){
            StudentDTO student = new StudentDTO();
            Student s = se.getStudent();
            Exam e = se.getExam();

            student.setDni(s.getDni());
            student.setName(s.getName());
            student.setLastname(s.getLastname());
            student.setEmail(s.getEmail());
            student.setExamDate(e.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/uuuu")));
            student.setExamTime(e.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
            student.setCompleteNameTeacher(teacher.getName() + " " + teacher.getLastname());

            result.add(student);
        }

        return result;
    }
}
