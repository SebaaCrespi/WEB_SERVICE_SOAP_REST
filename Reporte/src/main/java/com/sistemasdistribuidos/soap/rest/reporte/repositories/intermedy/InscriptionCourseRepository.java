package com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.InscriptionCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionCourseRepository extends JpaRepository<InscriptionCourse, Long> {


}
