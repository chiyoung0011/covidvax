package project.spring.covidvax.services;
import org.springframework.stereotype.Service;
import project.spring.covidvax.domain.VaccineCenter;
import project.spring.covidvax.repositories.VaccineCenterRepository;
import java.util.List;
@Service
public class VaccineCenterServiceImpl implements VaccineCenterService {
    private final VaccineCenterRepository vaccineCenterRepository;
    public VaccineCenterServiceImpl(VaccineCenterRepository vaccineCenterRepository) {
        this.vaccineCenterRepository = vaccineCenterRepository;
    }
    @Override
    public VaccineCenter findVaccineCenterById(Long id) {
        return vaccineCenterRepository.findById(id).get();
    }
    @Override
    public List<VaccineCenter> findAllVaccineCenters() {
        return vaccineCenterRepository.findAll();
    }
    @Override
    public VaccineCenter saveVaccineCenter(VaccineCenter vaccineCenter) {
        return vaccineCenterRepository.save(vaccineCenter);
    }
    @Override
    public List<VaccineCenter> findVaccineCenterByCity(String cityName) {
        return vaccineCenterRepository.findBySidoStartsWithOrderById(cityName);
    }
}
