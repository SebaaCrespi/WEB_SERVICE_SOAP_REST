package com.sistemasdistribuidos.soap.rest.reporte.dtos;

import com.sistemasdistribuidos.soap.rest.reporte.listed.YearCarrer;
import java.time.LocalTime;
import lombok.Data;

@Data
public class MatterDTO {

    private String name;

    private LocalTime timeSince;

    private LocalTime timeUntil;

    private YearCarrer yearCarrer;

}
