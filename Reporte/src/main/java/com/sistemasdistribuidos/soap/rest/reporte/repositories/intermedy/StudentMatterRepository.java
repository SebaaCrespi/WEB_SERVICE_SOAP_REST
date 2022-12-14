package com.sistemasdistribuidos.soap.rest.reporte.repositories.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.intermedy.StudentMatter;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMatterRepository extends JpaRepository<StudentMatter, Long> {

    List<StudentMatter> findByStudent_Id(long id);

    List<StudentMatter> findAllByMatter_IdIn(List<Long> matterIds);

}
