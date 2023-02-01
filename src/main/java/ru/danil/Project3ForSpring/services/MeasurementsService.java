package ru.danil.Project3ForSpring.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danil.Project3ForSpring.models.Measurements;
import ru.danil.Project3ForSpring.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurements measurements) {
        addTimeAndNameSensor(measurements);
        measurementsRepository.save(measurements);
    }

    private void addTimeAndNameSensor(Measurements measurements) {
        measurements.setSensor(sensorService.findByName(measurements.getSensor().getName()).get());
        measurements.setMasurementsTime(LocalDateTime.now())
        ;
    }

    public List<Measurements> findAll() {
        return measurementsRepository.findAll();
    }


}
