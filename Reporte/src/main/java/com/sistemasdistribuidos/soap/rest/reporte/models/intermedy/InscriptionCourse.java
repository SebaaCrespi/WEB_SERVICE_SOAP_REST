package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;


import com.sistemasdistribuidos.soap.rest.reporte.models.general.Course;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Inscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inscription_course")
public class InscriptionCourse {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "inscription_id")
    private Inscription inscription;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
