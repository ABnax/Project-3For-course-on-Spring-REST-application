package ru.danil.Project3ForSpring.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.danil.Project3ForSpring.models.Sensor;
import ru.danil.Project3ForSpring.services.SensorService;


@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorService.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "", "Имя данного сенсора есть в системе.");
    }
}

