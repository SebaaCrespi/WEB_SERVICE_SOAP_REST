package com.sistemasdistribuidos.soap.rest.reporte.models.general;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
@ApiModel(description = "Represents a course of a matter")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    @ApiModelProperty
    private long id;

    @Column
    @ApiModelProperty
    private String name;
}
