package com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals;

import com.sistemasdistribuidos.soap.rest.reporte.models.listed.YearCarrer;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class ExamPDF {

    private String matterName;

    private YearCarrer yearCarrer;

    private LocalDate date;

    private LocalTime time;

    private String teacherCompleteName;

    private String period;

    public String returnPeriodValue(int periodNumber){
        switch (periodNumber){
            case 1: return "PRIMER";
            case 2: return "SEGUNDO";
            case 3: return "TERCER";
            case 4: return "CUARTO";
            default: return "";
        }
    }

}
