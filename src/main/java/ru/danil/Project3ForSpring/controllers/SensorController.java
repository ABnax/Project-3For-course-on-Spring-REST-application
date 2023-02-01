package ru.danil.Project3ForSpring.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.danil.Project3ForSpring.dto.SensorDTO;
import ru.danil.Project3ForSpring.models.Sensor;
import ru.danil.Project3ForSpring.services.SensorService;
import ru.danil.Project3ForSpring.util.SensorErrorResponce;
import ru.danil.Project3ForSpring.util.SensorNotCreatedExeption;
import ru.danil.Project3ForSpring.util.SensorValidator;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final ModelMapper modelMapper;
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(ModelMapper modelMapper, SensorService sensorService, SensorValidator sensorValidator) {
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        Sensor tempSensor = convertToSensor(sensorDTO);
        sensorValidator.validate(tempSensor, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(';');
            }
            throw new SensorNotCreatedExeption(errorMsg.toString());
        }

        sensorService.save(tempSensor);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }


    @ExceptionHandler
    private ResponseEntity<SensorErrorResponce> handleExaption(SensorNotCreatedExeption e) {
        SensorErrorResponce response = new SensorErrorResponce(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}


