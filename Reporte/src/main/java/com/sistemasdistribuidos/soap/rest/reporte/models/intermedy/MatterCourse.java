package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Course;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matter_course")
public class MatterCourse {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "matter_id")
    private Matter matter;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate untilChangeNote;
}
