package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;


import com.sistemasdistribuidos.soap.rest.reporte.models.general.Course;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Inscription;
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
@Table(name = "inscription_course")
@ApiModel(description = "Represents a relationship for an inscription to a course")
public class InscriptionCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty
    private long id;

    @ManyToOne
    @JoinColumn(name = "inscription_id")
    @ApiModelProperty
    private Inscription inscription;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @ApiModelProperty
    private Course course;
}
