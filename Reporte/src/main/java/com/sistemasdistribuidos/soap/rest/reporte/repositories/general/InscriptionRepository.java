package com.sistemasdistribuidos.soap.rest.reporte.repositories.general;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {


}
