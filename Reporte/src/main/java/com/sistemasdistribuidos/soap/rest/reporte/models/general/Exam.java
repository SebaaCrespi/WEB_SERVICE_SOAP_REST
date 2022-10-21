package com.sistemasdistribuidos.soap.rest.reporte.models.general;

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
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "fecha")
    private LocalDate fecha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matter_id", referencedColumnName = "id_matter")
    private Matter matter;
}
