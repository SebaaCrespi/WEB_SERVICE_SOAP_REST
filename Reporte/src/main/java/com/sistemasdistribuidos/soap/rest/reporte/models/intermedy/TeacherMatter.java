package com.sistemasdistribuidos.soap.rest.reporte.models.intermedy;

import com.sistemasdistribuidos.soap.rest.reporte.models.general.Matter;
import com.sistemasdistribuidos.soap.rest.reporte.models.user.Teacher;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_matter")
@ApiModel(description = "Represents a relationship for the teacher of a matter")
public class TeacherMatter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_teacher_id")
    @ApiModelProperty
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "matter_id")
    @ApiModelProperty
    private Matter matter;
}
