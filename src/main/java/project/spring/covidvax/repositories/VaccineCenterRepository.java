package project.spring.covidvax.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import project.spring.covidvax.domain.VaccineCenter;

import java.util.List;

public interface VaccineCenterRepository extends JpaRepository<VaccineCenter, Long> {
    List<VaccineCenter> findBySidoStartsWithOrderById(String cityName);
}