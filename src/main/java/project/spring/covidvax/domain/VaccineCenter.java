package project.spring.covidvax.domain;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
public class VaccineCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String centerName, sido, sigungu, imgurl, facilityName, address, centerType, org, phoneNumber, zipCode;
    private double lat, lng;
    private ZonedDateTime createdAt, updatedAt;
}