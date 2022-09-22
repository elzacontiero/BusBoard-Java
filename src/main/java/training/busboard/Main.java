package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
class PostCodeResult {
    public String postcode;
    public Double longitude;
    public Double latitude;

    @Override
    public String toString() {
        return "PostCodeResult{" +
                "postcode='" + postcode + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PostCodeInfo {
    public Integer status;
    public PostCodeResult result;

    public static PostCodeInfo getPostCodeInfo(String postCode) {

        String endpoint = "http://api.postcodes.io/postcodes/" + postCode;
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        PostCodeInfo response = client.target(endpoint)
                .request(MediaType.APPLICATION_JSON)
                .get(PostCodeInfo.class);
        return response;
    }
    @Override
    public String toString() {
        return "PostCodeInfo{" +
                "status=" + status +
                ", result=" + result +
                '}';
    }
}

public class Main {

    /* unused code.
    public static void part1() {
        String tflEndpoint = "https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals";
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        List<BusArrival> response = client.target(tflEndpoint)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<BusArrival>>() {
                });

        for (BusArrival bus : response) {
            System.out.println(bus);
        }
    }
    */

    public static void main(String args[]) {

        PostCodeInfo postcodeInfo = PostCodeInfo.getPostCodeInfo("NW3 4BJ");
        System.out.println(postcodeInfo);
        StopPointsWithin stops = StopPointsWithin.getStopPointsWithin(postcodeInfo.result.latitude, postcodeInfo.result.longitude);

        for (StopPointsWithinInfo info : stops.stopPoints) {
            System.out.println(info);
        }

    }

}

