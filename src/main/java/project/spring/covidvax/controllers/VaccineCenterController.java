package project.spring.covidvax.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.spring.covidvax.domain.VaccineCenter;
import project.spring.covidvax.services.VaccineCenterService;
import java.util.List;
@RestController
@RequestMapping(VaccineCenterController.BASE_URL)
public class VaccineCenterController {
    public static final String BASE_URL = "/api/v1/vaccinecenters";
    private final VaccineCenterService vaccineCenterService;
    public VaccineCenterController(VaccineCenterService vaccineCenterService) {
        this.vaccineCenterService = vaccineCenterService;
    }
    @GetMapping
    public List<VaccineCenter> getAllVaccineCenter() {
        System.out.println("all");
        return vaccineCenterService.findAllVaccineCenters();
    }
    @GetMapping("/{id}")
    public VaccineCenter getVaccineCenterById(@PathVariable Long id) {
        System.out.println("byid " + id);
        return vaccineCenterService.findVaccineCenterById(id);
    }
    @GetMapping("/name/{cityName}")
    public List<VaccineCenter> getVaccineCenterByCity(@PathVariable("cityName") String cityName) {
        System.out.println("byname " + cityName);
        return vaccineCenterService.findVaccineCenterByCity(cityName);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineCenter saveVaccineCenter(@RequestBody VaccineCenter vaccineCenter) {
        System.out.println("post");
        return vaccineCenterService.saveVaccineCenter(vaccineCenter);
    }
}
