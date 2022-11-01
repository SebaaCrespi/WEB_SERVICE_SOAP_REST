package com.sistemasdistribuidos.soap.rest.reporte.repositories.user;

import com.sistemasdistribuidos.soap.rest.reporte.models.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {


}
