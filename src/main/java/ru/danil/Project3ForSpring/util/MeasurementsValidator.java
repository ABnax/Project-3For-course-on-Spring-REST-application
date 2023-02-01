package ru.danil.Project3ForSpring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.danil.Project3ForSpring.models.Measurements;
import ru.danil.Project3ForSpring.services.SensorService;


@Component
public class MeasurementsValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementsValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Measurements.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurements measurements = (Measurements) target;

        if (measurements.getSensor() == null) {
            return;
        }

        if (sensorService.findByName(measurements.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "", "Данного сенсора не существует в системе.");
    }

}
