package auction.auction;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stolbunovd
 * on 9/28/18
 */
public class MainTestClass {

    private static final String vehicleFinder = "https://www.copart.com/public/vehicleFinder/search";
    private static final Header userAgent = new BasicHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

    public static void main(String[] args) {
        first();
    }

    private static void first() {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            String incapCookie = getCookie(client);
            HttpPost post = new HttpPost(vehicleFinder);
            post.setHeader(userAgent);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            post.setHeader("Cookie", incapCookie);
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("sort", "auction_date_type desc,auction_date_utc asc"));
            params.add(new BasicNameValuePair("filter[MISC]", "#MakeCode:FORD OR #MakeDesc:Ford,#LotModel:FUSION,#VehicleTypeCode:VEHTYPE_V,#LotYear:[2016 TO 2019]"));
            params.add(new BasicNameValuePair("filter[ENGN]", "engine:\"1.5L  4\""));
            params.add(new BasicNameValuePair("filter[SDAT]", "auction_date_utc:[\"2018-09-28T00:00:00Z\" TO \"2018-09-28T23:59:59Z\"]"));
            post.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse response = client.execute(post);
            System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static String getCookie(HttpClient client) throws IOException {
        HttpUriRequest request = RequestBuilder.get()
                .setUri("http://www.copart.com")
                .addHeader(userAgent)
                .build();
        HttpResponse response = client.execute(request);
        Header[] cookies = response.getHeaders("Set-Cookie");
        String visid = null;
        String incap = null;
        for (Header header : cookies) {
            String cookieValue = header.getValue();
            if (cookieValue.contains("visid")) {
                visid = cookieValue.substring(0,
                        cookieValue.indexOf(";") + 1);
            }
            if (cookieValue.contains("incap_ses")) {
                incap = cookieValue.substring(0,
                        cookieValue.indexOf(";"));
            }
        }
        System.out.println(visid + " " + incap);
        return visid + " " + incap;
    }

}
