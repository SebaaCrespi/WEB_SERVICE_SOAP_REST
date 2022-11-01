package com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.InscriptionExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionExamRepository extends JpaRepository<InscriptionExam, Long> {


}
