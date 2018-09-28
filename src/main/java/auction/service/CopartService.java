package auction.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

/**
 * Created by stolbunovd
 * on 9/27/18
 */
@Service
public class CopartService {

    private final String main = "https://www.copart.com";
    private final String vehicleFinder = main + "/public/vehicleFinder/search";

    @Autowired
    private RestTemplate restTemplate;

    public String readCopart() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        headers.add("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("accept-encoding", "zip, deflate, br");
        headers.add("accept-language", "en-US,en;q=0.9");
        headers.add("x-xsrf-token", "c8f5ed1a-8f3c-40db-9948-dd25d6c977bf");
        headers.add("origin", "https://www.copart.com");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("sort", "auction_date_type desc,auction_date_utc asc");
        map.add("filter[MISC]", "#MakeCode:FORD OR #MakeDesc:Ford,#LotModel:FUSION,#VehicleTypeCode:VEHTYPE_V,#LotYear:[2016 TO 2019]");
        map.add("filter[ENGN]", "engine:\"1.5L  4\"");
        map.add("filter[FETI]", "lot_condition_code:CERT-D");
        map.add("filter[SDAT]", "auction_date_utc:[\"2018-09-28T00:00:00Z\" TO \"2018-09-28T23:59:59Z\"]");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(vehicleFinder, request, String.class);
        return response.getBody();
    }

    public String readCopart2() throws IOException {
        Document document = Jsoup.connect(main).get();
        return document.html();
    }

    public String getIncapsulaCookie() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        headers.add("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("accept-encoding", "zip, deflate, br");
        headers.add("accept-language", "en-US,en;q=0.9");
        return null;
    }

}
