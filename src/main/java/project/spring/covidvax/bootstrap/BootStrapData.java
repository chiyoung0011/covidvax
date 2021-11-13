package project.spring.covidvax.bootstrap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.spring.covidvax.domain.VaccineCenter;
import project.spring.covidvax.repositories.VaccineCenterRepository;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
@Component
public class BootStrapData implements CommandLineRunner {
    private final VaccineCenterRepository vaccineCenterRepository;
    public BootStrapData(VaccineCenterRepository vaccineCenterRepository) {
        this.vaccineCenterRepository = vaccineCenterRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading Vaccine Center Data");
        URL url = new URL("https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=1000&serviceKey=dvmMY0GFhb6DhxQvyK9usqCd0UucqR1tDRYoV4mnNhJQV82ZtMm3ecrR6mEGegvU4vDMpDNb9Gkela%2Bphcu4Ew%3D%3D");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String inline = "";
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) throw new RuntimeException("HttpResponseCode: " + responsecode);
        else {
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            scanner.close();
        }
        JSONParser simpleparser = new JSONParser();
        JSONObject obj = (JSONObject) simpleparser.parse(inline);
        Long currentCount = (Long) obj.get("currentCount");
        JSONArray jarr = (JSONArray) (obj.get("data"));
        VaccineCenter vax = new VaccineCenter();
        JSONObject elem = new JSONObject();
        LocalDateTime ldt_c, ldt_u;
        ZonedDateTime zdt_c, zdt_u;
        DateTimeFormatter datetimeformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i=0; i< currentCount; i++) {
            elem = (JSONObject) jarr.get(i);
            vax = new VaccineCenter();
            vax.setZipCode((String) elem.get("zipCode"));
            vax.setAddress((String) elem.get("address"));
            vax.setCenterName((String) elem.get("centerName"));
            vax.setCenterType((String) elem.get("centerType"));
            ldt_c = LocalDateTime.parse(((String) elem.get("createdAt")), datetimeformat);
            zdt_c = ldt_c.atZone(ZoneId.of("Asia/Seoul"));
            vax.setCreatedAt(zdt_c);
            vax.setLat(Double.parseDouble((String) elem.get("lat")));
            vax.setLng(Double.parseDouble((String) elem.get("lng")));
            vax.setOrg((String) elem.get("org"));
            vax.setSido((String) elem.get("sido"));
            vax.setSigungu((String) elem.get("sigungu"));
            ldt_u = LocalDateTime.parse(((String) elem.get("updatedAt")), datetimeformat);
            zdt_u = ldt_u.atZone(ZoneId.of("Asia/Seoul"));
            vax.setUpdatedAt(zdt_u);
            vax.setPhoneNumber((String) elem.get("phoneNumber"));
            vax.setFacilityName((String) elem.get("facilityName"));
            switch ((String) elem.get("sido")) {
                case "서울특별시":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Flag_of_Seoul.svg/1280px-Flag_of_Seoul.svg.png");
                    break;
                case "충청남도":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Flag_of_South_Chungcheong_Province.svg/1280px-Flag_of_South_Chungcheong_Province.svg.png");
                    break;
                case "광주광역시":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Flag_of_Gwangju.svg/1280px-Flag_of_Gwangju.svg.png");
                    break;
                case "경상남도":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Flag_of_South_Gyeongsang_Province.svg/1280px-Flag_of_South_Gyeongsang_Province.svg.png");
                    break;
                case "대구광역시":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Daegu.svg/1280px-Flag_of_Daegu.svg.png");
                    break;
                case "부산광역시":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Flag_of_Busan.svg/1280px-Flag_of_Busan.svg.png");
                    break;
                case "인천광역시":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Flag_of_Incheon.svg/290px-Flag_of_Incheon.svg.png");
                    break;
                case "대전광역시":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Flag_of_Daejeon.svg/1280px-Flag_of_Daejeon.svg.png");
                    break;
                case "울산광역시":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Flag_of_Ulsan.svg/1280px-Flag_of_Ulsan.svg.png");
                    break;
                case "세종특별자치시":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Flag_of_Sejong_City%2C_South_Korea.svg/1280px-Flag_of_Sejong_City%2C_South_Korea.svg.png");
                    break;
                case "경기도":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Flag_of_Gyeonggi_Province_%282021%29.svg/1280px-Flag_of_Gyeonggi_Province_%282021%29.svg.png");
                    break;
                case "강원도":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Flag_of_Gangwon_Province.svg/1280px-Flag_of_Gangwon_Province.svg.png");
                    break;
                case "충청북도":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Flag_of_North_Chungcheong_Province.svg/1280px-Flag_of_North_Chungcheong_Province.svg.png");
                    break;
                case "전라북도":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Flag_of_North_Jeolla_Province.svg/1280px-Flag_of_North_Jeolla_Province.svg.png");
                    break;
                case "전라남도":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Flag_of_South_Jeolla_Province.svg/1280px-Flag_of_South_Jeolla_Province.svg.png");
                    break;
                case "경상북도":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Flag_of_North_Gyeongsang_Province.svg/1280px-Flag_of_North_Gyeongsang_Province.svg.png");
                    break;
                case "제주특별자치도":
                    vax.setImgurl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Flag_of_Jeju.svg/1280px-Flag_of_Jeju.svg.png");
                    break;
            }
            vaccineCenterRepository.save(vax);
        }
        System.out.println("Loading Vaccine Center Data Finished");
    }
}
