package com.sistemasdistribuidos.soap.rest.reporte.repositories.user;

import com.sistemasdistribuidos.soap.rest.reporte.models.user.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}
