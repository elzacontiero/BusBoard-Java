package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;
import java.util.List;
import jakarta.ws.rs.core.GenericType;

@JsonIgnoreProperties(ignoreUnknown = true)
class BusArrival {

    public String id;
    public String vehicleId;
    public String stationName;
    public String lineId;
    public String lineName;
    public String platformName;
    public String direction;
    public Integer timeToStation;
    public String destinationName;
    public String timeStamp;
    public String currentLocation;
    public String towards;
    public String expectedArrival;
    @Override
    public String toString() {
        return "BusArrival{" +
                "id='" + id + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", lineId='" + lineId + '\'' +
                ", lineName='" + lineName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", direction='" + direction + '\'' +
                ", timeToStation=" + timeToStation +
                ", destinationName='" + destinationName + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", towards='" + towards + '\'' +
                ", expectedArrival='" + expectedArrival + '\'' +
                '}';
    }
}
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

    @Override
    public String toString() {
        return "PostCodeInfo{" +
                "status=" + status +
                ", result=" + result +
                '}';
    }
}

public class Main {
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
    public static List<BusArrival> givenStopCode(String stopPoint) {

        String endpoint = "https://api.tfl.gov.uk/StopPoint/" + stopPoint + "/Arrivals";
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        List<BusArrival> response = client.target(endpoint)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<BusArrival>>() {
                });
        return response;
    }

    public static PostCodeInfo getPostCodeInfo (String postCode) {

        String endpoint = "http://api.postcodes.io/postcodes/" + postCode;
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        PostCodeInfo response = client.target(endpoint)
                .request(MediaType.APPLICATION_JSON)
                .get(PostCodeInfo.class);
        return response;
    }

    public static void main(String args[]) {
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "3128");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "3128");

        PostCodeInfo postCodeInfo = getPostCodeInfo ("NW3 4BJ");
        System.out.println(postCodeInfo);

        //List<BusArrival> listOfBusArrival = givenStopCode("490008660N");
        //for (BusArrival bus : listOfBusArrival) { // for each bus in listOfBusArrival, print the bus
           //System.out.println(bus);
        }

    }

