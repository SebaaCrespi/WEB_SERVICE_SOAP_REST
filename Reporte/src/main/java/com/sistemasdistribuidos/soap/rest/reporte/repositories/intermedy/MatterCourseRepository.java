package com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.MatterCourse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatterCourseRepository extends JpaRepository<MatterCourse, Long> {

    List<MatterCourse> findAllByCourse_Id(long courseId);

}
