package ru.danil.Project3ForSpring.dto;


import javax.validation.constraints.*;


public class MeasurementsDTO {

    @NotNull(message = "Значения сенсора не может быть пустым.")
    @Min(value = -100, message = "value > -100")
    @Max(value = 100, message = "value < 100")
    private double value;

    @NotNull
    private boolean isRaining;

    @NotNull
    private SensorDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return isRaining;
    }

    public void setRaining(Boolean raining) {
        isRaining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
