package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Exam;
import com.sistemasdistribuidos.soap.rest.reporte.models.general.Inscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inscription_exam")
public class InscriptionExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "inscription_id")
    private Inscription inscription;
}
