package com.sistemasdistribuidos.soap.rest.reporte.models.user;

import io.swagger.annotations.ApiModel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@ApiModel(description = "Represents Teacher User")
public class Teacher extends User {

}
