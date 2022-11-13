package com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.TeacherMatter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMatterRepository extends JpaRepository<TeacherMatter, Long> {

    List<TeacherMatter> findAllByMatter_IsFirstFourMonth(boolean isFirstFourMonth);

    List<TeacherMatter> findAllByMatter_IdIn(List<Long> ids);

}
