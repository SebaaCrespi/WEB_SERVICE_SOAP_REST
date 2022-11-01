package com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.StudentExam;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {

    List<StudentExam> findByStudent_Id(long id);

}
