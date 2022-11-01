package com.sistemasdistribuidos.soap.rest.reporte.dto.pdf;

import com.sistemasdistribuidos.soap.rest.reporte.dto.pdf.generals.MatterPDF;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class AnalyticPDF {

    private String courseName;

    private String completeName;

    private String dni;

    private List<MatterPDF> matters1;

    private List<MatterPDF> matters2;

    private List<MatterPDF> matters3;

    private List<MatterPDF> matters4;

    private float average;

    public float calculateAverage(){
        List<MatterPDF> allMatters = new ArrayList<>();
        allMatters.addAll(matters1);
        allMatters.addAll(matters2);
        allMatters.addAll(matters3);
        allMatters.addAll(matters4);

        float accumulated = 0;
        int quantity = allMatters.size();

        for (MatterPDF m : allMatters){
            accumulated = accumulated + m.getNote();
        }

        return accumulated / quantity;
    }

}
