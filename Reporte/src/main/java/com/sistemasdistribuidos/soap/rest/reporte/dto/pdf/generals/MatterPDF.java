package com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals;

import com.sistemasdistribuidos.soap.rest.reporte.models.listed.YearCarrer;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class MatterPDF {

    private String name;

    private float note;

    private LocalDate date;

    private String year;

    private String turn;

    private LocalTime timeSince;

    private LocalTime timeUntil;

    private boolean isFirstFourMonth;

    private String teacherCompleteName;

    private YearCarrer yearCarrer;

}
