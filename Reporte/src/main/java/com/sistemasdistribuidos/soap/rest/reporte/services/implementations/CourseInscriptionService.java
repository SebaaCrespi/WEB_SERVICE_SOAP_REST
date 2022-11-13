package com.sistemasdistribuidos.soap.rest.reporte.services.implementations;

import com.sistemasdistribuidos.soap.rest.reporte.dto.excel.StudentDTO;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Course;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.InscriptionCourse;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.MatterCourse;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.StudentMatter;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Student;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.InscriptionCourseRepository;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.MatterCourseRepository;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.StudentMatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.ICourseInscriptionService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseInscriptionService implements ICourseInscriptionService {

    @Autowired
    InscriptionCourseRepository inscriptionCourseRepository;

    @Autowired
    MatterCourseRepository matterCourseRepository;

    @Autowired
    StudentMatterRepository studentMatterRepository;

    @Override
    public List<StudentDTO> getStudentsByMatterInscriptionExcel(long matterId, LocalDate since,
            LocalDate until) {

        since = since == null ? LocalDate.of(LocalDate.now().getYear(), 1,1) : since;
        until = until == null ? LocalDate.of(LocalDate.now().getYear(), 12,31) : until;

        LocalDate finalSince = since;
        LocalDate finalUntil = until;
        List<Course> courseList = inscriptionCourseRepository.findAll()
                .stream().filter(ic -> ic.getInscription().getTimeSince().isAfter(finalSince) &&
                                ic.getInscription().getTimeUtil().isBefore(finalUntil))
                .map(InscriptionCourse::getCourse)
                .collect(Collectors.toList());

        List<Matter> matterList = matterCourseRepository.findAllByMatter_IdAndCourse_IdIn(matterId,
                courseList.stream().map(Course::getId).collect(Collectors.toList())).stream()
                .map(MatterCourse::getMatter).collect(Collectors.toList());

        List<StudentMatter> studentMatters = studentMatterRepository.findAllByMatter_IdIn(
                matterList.stream().map(Matter::getId).collect(Collectors.toList()));

        List<StudentDTO> result = new ArrayList<>();
        for (StudentMatter sm : studentMatters){
            StudentDTO student = new StudentDTO();
            Student s = sm.getStudent();

            student.setDni(s.getDni());
            student.setName(s.getName());
            student.setLastname(s.getLastname());
            student.setEmail(s.getEmail());

            result.add(student);
        }

        return result;
    }
}
