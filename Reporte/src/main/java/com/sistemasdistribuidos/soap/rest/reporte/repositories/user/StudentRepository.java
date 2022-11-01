package com.sistemasdistribuidos.soap.rest.reporte.repositories.user;

import com.sistemasdistribuidos.soap.rest.reporte.models.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


}
