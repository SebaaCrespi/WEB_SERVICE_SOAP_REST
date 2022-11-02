package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Student;
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
@Table(name = "student_matter")
@ApiModel(description = "Represents a relationship for the matters of a student")
public class StudentMatter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_student_id")
    @ApiModelProperty
    private Student student;

    @ManyToOne
    @JoinColumn(name = "matter_id")
    @ApiModelProperty
    private Matter matter;

    @Column
    @ApiModelProperty
    private float noteFirst;

    @Column
    @ApiModelProperty
    private float noteSecond;

}
