package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Exam;
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
@Table(name = "inscription_exam")
@ApiModel(description = "Represents a relationship for an inscription to an exam")
public class InscriptionExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty
    private long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    @ApiModelProperty
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "inscription_id")
    @ApiModelProperty
    private Inscription inscription;
}
