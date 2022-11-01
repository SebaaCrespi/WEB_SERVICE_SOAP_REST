package com.sistemasdistribuidos.soap.rest.reporte.services.implementations;

import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals.MatterPDF;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.MatterCourse;
import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.TeacherMatter;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Teacher;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.MatterCourseRepository;
import com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy.TeacherMatterRepository;
import com.sistemasdistribuidos.soap.rest.reporte.services.IMatterService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatterService implements IMatterService {

    @Autowired
    MatterCourseRepository matterCourseRepository;

    @Autowired
    TeacherMatterRepository teacherMatterRepository;

    @Override
    public List<MatterPDF> buildMatterByFourthPDF(int valueMont, String turn, long courseId) {
        List<MatterPDF> matterPDFS = new ArrayList<>();
        boolean isFirstFourth = valueMont == 1;
        //List<MatterCourse> matterCourses = matterCourseRepository.findAllByCourse_Id(courseId);
        List<TeacherMatter> teacherMatters = teacherMatterRepository.findAllByMatter_IsFirstFourMonth(isFirstFourth);

        for (TeacherMatter tm : teacherMatters){
            Matter m = tm.getMatter();
            Teacher t = tm.getTeacher();

            MatterPDF matter = new MatterPDF();
            matter.setFirstFourMonth(m.isFirstFourMonth());
            matter.setName(m.getName());
            matter.setTimeSince(m.getTimeSince());
            matter.setTimeUntil(m.getTimeUntil());
            matter.setTeacherCompleteName(t.getName() + " " + t.getLastname());
            matter.setYearCarrer(m.getYearCarrer());

            matterPDFS.add(matter);
        }

        return matterPDFS;
    }

}
