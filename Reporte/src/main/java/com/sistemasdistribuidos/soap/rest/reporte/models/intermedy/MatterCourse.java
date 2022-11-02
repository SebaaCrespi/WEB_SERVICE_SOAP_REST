package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Course;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Represents a relationship for the courses of a matter")
public class MatterCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty
    private long id;

    @ManyToOne
    @JoinColumn(name = "matter_id")
    @ApiModelProperty
    private Matter matter;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @ApiModelProperty
    private Course course;

    @ApiModelProperty
    private LocalDate untilChangeNote;
}
