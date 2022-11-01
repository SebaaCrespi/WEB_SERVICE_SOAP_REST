package com.sistemasdistribuidos.soap.rest.reporte.repositories.general;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


}
