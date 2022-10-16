package com.sistemas.distribuidos.soap.rest.entity.intermedy;

import com.sistemas.distribuidos.soap.rest.entity.general.Course;
import com.sistemas.distribuidos.soap.rest.entity.general.Inscription;
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
