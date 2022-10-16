package com.sistemas.distribuidos.soap.rest.entity.intermedy;

import com.sistemas.distribuidos.soap.rest.entity.general.Course;
import com.sistemas.distribuidos.soap.rest.entity.general.Matter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matter_course")
public class MatterCourse {

    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "matter_id")
    private Matter matter;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate untilChangeNote;
}
