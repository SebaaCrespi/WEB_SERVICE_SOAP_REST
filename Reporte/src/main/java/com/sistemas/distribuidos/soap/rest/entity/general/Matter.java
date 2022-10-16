package com.sistemas.distribuidos.soap.rest.entity.general;

import com.sistemas.distribuidos.soap.rest.listed.YearCarrer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

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

    @Column
    private boolean isFirtsFourMonth;

    @Enumerated(EnumType.STRING)
    private YearCarrer yearCarrer;

}
