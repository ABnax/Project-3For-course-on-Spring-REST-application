package ru.danil.Project3ForSpring.dto;

import java.util.List;

public class MeasurementsListDTO {
    private List<MeasurementsDTO> measurementsDTO;

    public MeasurementsListDTO(List<MeasurementsDTO> measurementsDTO) {
        this.measurementsDTO = measurementsDTO;
    }

    public List<MeasurementsDTO> getMeasurements() {
        return measurementsDTO;
    }

    public void setMeasurements(List<MeasurementsDTO> measurementsDTO) {
        this.measurementsDTO = measurementsDTO;
    }
}