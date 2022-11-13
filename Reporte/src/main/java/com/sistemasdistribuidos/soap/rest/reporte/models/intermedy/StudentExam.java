package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Exam;
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
@Table(name = "student_exam")
@ApiModel(description = "Represents a relationship for the exams of a student")
public class StudentExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_student_id")
    @ApiModelProperty
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    @ApiModelProperty
    private Exam exam;

    @Column
    @ApiModelProperty
    private float note;
}
