package com.sistemasdistribuidos.soap.rest.reporte.repositories.general;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatterRepository extends JpaRepository<Matter, Long> {

    List<Matter> getAllByIsFirstFourMonth(boolean isFirstFourMonth);

}
