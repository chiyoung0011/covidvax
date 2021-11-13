package project.spring.covidvax.services;
import project.spring.covidvax.domain.VaccineCenter;
import java.util.List;
public interface VaccineCenterService {
    VaccineCenter findVaccineCenterById(Long id);
    List<VaccineCenter> findAllVaccineCenters();
    VaccineCenter saveVaccineCenter(VaccineCenter vaccineCenter);
    List<VaccineCenter> findVaccineCenterByCity(String cityName);
}
