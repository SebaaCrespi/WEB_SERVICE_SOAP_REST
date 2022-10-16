package com.sistemas.distribuidos.soap.rest.entity.intermedy;

import com.sistemas.distribuidos.soap.rest.entity.general.Matter;
import com.sistemas.distribuidos.soap.rest.entity.user.Teacher;
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
