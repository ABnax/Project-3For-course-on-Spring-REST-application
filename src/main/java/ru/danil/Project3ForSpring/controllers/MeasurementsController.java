package ru.danil.Project3ForSpring.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.danil.Project3ForSpring.dto.MeasurementsDTO;
import ru.danil.Project3ForSpring.dto.MeasurementsListDTO;
import ru.danil.Project3ForSpring.models.Measurements;
import ru.danil.Project3ForSpring.services.MeasurementsService;
import ru.danil.Project3ForSpring.util.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final ModelMapper modelMapper;
    private final MeasurementsService measurementsService;
    private final MeasurementsValidator measurementsValidator;

    @Autowired
    public MeasurementsController(ModelMapper modelMapper, MeasurementsService measurementsService,
                                  MeasurementsValidator measurementsValidator) {
        this.modelMapper = modelMapper;
        this.measurementsService = measurementsService;
        this.measurementsValidator = measurementsValidator;
    }

    @GetMapping
    public MeasurementsListDTO getAllMeasurements() {
        return new MeasurementsListDTO(measurementsService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }


    @GetMapping("/rainyDaysCount")
    public long countRainDay() {
        return measurementsService.findAll().stream().filter(a -> a.isRaining()).count();
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addForecast(@RequestBody @Valid MeasurementsDTO measurementsDTO, BindingResult bindingResult) {

        Measurements tempMeasurements = convertToMeasurements(measurementsDTO);
        measurementsValidator.validate(tempMeasurements, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                StringBuilder append = errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(';');
            }
            throw new MeasurementsExeption(errorMsg.toString());
        }

        measurementsService.save(tempMeasurements);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurements convertToMeasurements(MeasurementsDTO measurementsDTO) {
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    private MeasurementsDTO convertToMeasurementDTO(Measurements measurement) {
        return modelMapper.map(measurement, MeasurementsDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponce> handleExaption(MeasurementsExeption e) {
        SensorErrorResponce response = new SensorErrorResponce(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
