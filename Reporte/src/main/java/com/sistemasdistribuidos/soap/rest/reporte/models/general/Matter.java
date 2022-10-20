package com.sistemasdistribuidos.soap.rest.reporte.models.general;

import com.sistemasdistribuidos.soap.rest.reporte.models.listed.YearCarrer;
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
public class Matter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matter")
    private long id;

    @Column(name = "name")
    private String name;

    @Column
    private LocalTime timeSince;

    @Column
    private LocalTime timeUntil;

    @Column(columnDefinition = "BOOLEAN")
    private boolean isFirstFourMonth;

    @Enumerated(EnumType.STRING)
    private YearCarrer yearCarrer;

}
