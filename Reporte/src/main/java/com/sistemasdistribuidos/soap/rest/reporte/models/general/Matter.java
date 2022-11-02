package com.sistemasdistribuidos.soap.rest.reporte.models.general;

import com.sistemasdistribuidos.soap.rest.reporte.models.listed.YearCarrer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matter")
@ApiModel(description = "Represents a matter")
public class Matter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matter")
    @ApiModelProperty
    private long id;

    @Column(name = "name")
    @ApiModelProperty
    private String name;

    @Column(name = "turn")
    @ApiModelProperty
    private String turn;

    @Column
    @ApiModelProperty
    private LocalTime timeSince;

    @Column
    @ApiModelProperty
    private LocalTime timeUntil;

    @Column(columnDefinition = "BOOLEAN")
    @ApiModelProperty
    private boolean isFirstFourMonth;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty
    private YearCarrer yearCarrer;

}
