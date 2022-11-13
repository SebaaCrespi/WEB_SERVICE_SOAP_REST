package com.sistemasdistribuidos.soap.rest.reporte.models.user;

import io.swagger.annotations.ApiModel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@ApiModel(description = "Represents Admin User")
public class Admin extends User {

}
