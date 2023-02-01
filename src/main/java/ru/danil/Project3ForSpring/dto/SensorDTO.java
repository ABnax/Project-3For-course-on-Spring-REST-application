package ru.danil.Project3ForSpring.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Имя сенсора не может быть пустым.")
    @Size(min = 3, max = 30, message = "Название сенсора не должно быть меньше 3 и больше 30 символов.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
