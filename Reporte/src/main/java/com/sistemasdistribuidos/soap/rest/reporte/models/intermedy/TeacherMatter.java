package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_matter")
public class TeacherMatter {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "matter_id")
    private Matter matter;
}
