package com.sistemas.distribuidos.soap.rest.entity.intermedy;

import com.sistemas.distribuidos.soap.rest.entity.general.Exam;
import com.sistemas.distribuidos.soap.rest.entity.user.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_exam")
public class StudentExam {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private int note;
}
