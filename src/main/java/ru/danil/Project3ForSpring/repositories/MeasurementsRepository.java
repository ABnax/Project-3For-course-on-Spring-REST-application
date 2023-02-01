package ru.danil.Project3ForSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danil.Project3ForSpring.models.Measurements;

public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
}
