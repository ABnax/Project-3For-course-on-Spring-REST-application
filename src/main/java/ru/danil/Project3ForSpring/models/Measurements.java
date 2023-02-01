package ru.danil.Project3ForSpring.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurements {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull(message = "Значения сенсора не может быть пустым.")
    @Min(value = -100, message = "value > -100")
    @Max(value = 100, message = "value < 100")
    private double value;

    @Column(name = "raining")
    @NotNull(message = "Значения raining не может быть пустым.")
    private boolean raining;

    @ManyToOne
    @NotNull(message = "Имя сенсора не может быть пустым.")
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    @Column(name = "measurements_time")
    private LocalDateTime masurementsTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getMasurementsTime() {
        return masurementsTime;
    }

    public void setMasurementsTime(LocalDateTime masurementsTime) {
        this.masurementsTime = masurementsTime;
    }


}
