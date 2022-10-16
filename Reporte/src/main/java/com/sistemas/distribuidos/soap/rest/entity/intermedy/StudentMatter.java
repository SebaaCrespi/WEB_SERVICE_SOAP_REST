package com.sistemas.distribuidos.soap.rest.entity.intermedy;

import com.sistemas.distribuidos.soap.rest.entity.general.Matter;
import com.sistemas.distribuidos.soap.rest.entity.user.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_matter")
public class StudentMatter {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "matter_id")
    private Matter matter;

    private int noteFirts;
    private int noteSecond;

}
