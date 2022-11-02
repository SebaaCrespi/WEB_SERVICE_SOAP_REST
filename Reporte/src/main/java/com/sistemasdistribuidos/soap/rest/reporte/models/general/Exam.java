package com.sistemasdistribuidos.soap.rest.reporte.models.general;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exam")
@ApiModel(description = "Represents an exam of a matter")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty
    private long id;

    @Column(name = "hora")
    @ApiModelProperty
    private LocalTime hora;

    @Column(name = "fecha")
    @ApiModelProperty
    private LocalDate fecha;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "matter_id", referencedColumnName = "id_matter")
    @ApiModelProperty
    private Matter matter;
}
