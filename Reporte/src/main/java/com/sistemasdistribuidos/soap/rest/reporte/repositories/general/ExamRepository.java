package com.sistemasdistribuidos.soap.rest.reporte.repositories.general;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Exam;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findAllByFechaBetween(LocalDate desde, LocalDate hasta);


}
