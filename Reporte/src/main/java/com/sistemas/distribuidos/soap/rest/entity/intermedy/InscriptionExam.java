package com.sistemas.distribuidos.soap.rest.entity.intermedy;

import com.sistemas.distribuidos.soap.rest.entity.general.Exam;
import com.sistemas.distribuidos.soap.rest.entity.general.Inscription;
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
    private long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "inscription_id")
    private Inscription inscription;
}
