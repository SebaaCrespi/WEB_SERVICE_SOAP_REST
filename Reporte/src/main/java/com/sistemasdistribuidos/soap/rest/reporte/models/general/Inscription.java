package com.sistemasdistribuidos.soap.rest.reporte.models.general;

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
@Table(name = "inscription")
@ApiModel(description = "Represents an inscription for an exam or course")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscription")
    @ApiModelProperty
    private long id;

    @ApiModelProperty
    private LocalDate timeSince;

    @ApiModelProperty
    private LocalDate timeUtil;
}
