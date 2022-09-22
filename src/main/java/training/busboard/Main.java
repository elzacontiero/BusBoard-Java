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

@JsonIgnoreProperties(ignoreUnknown = true)
class StopPointsWithinInfo {
    public String naptanId;
    public String commonName;
    public Double distance;

    @Override
    public String toString() {
        return "Info{" +
                "naptanId='" + naptanId + '\'' +
                ", commonName='" + commonName + '\'' +
                ", distance=" + distance +
                '}';
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class StopPointsWithin {

    public StopPointsWithinInfo[] stopPoints;

    @Override
    public String toString() {
        return "StopPointsWithin{" +
                "stopPoints=" + Arrays.toString(stopPoints) +
                '}';
    }
}

public class Main {

    // Part 1 --------------------
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

    // Part 2 --------------------



    // I create a method that given a lat and longitude it returns a list of StopPoints within
    public static StopPointsWithin getStopPointsWithin(double lat, double lon) {
        String endpoint = "https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanBusWayPoint," +
                "NaptanOnstreetBusCoachStopCluster,NaptanOnstreetBusCoachStopPair," +
                "NaptanPrivateBusCoachTram," +
                "NaptanPublicBusCoachTram&modes=bus&lat=" + lat + "&lon=" + lon;
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        StopPointsWithin response = client.target(endpoint)
                .request(MediaType.APPLICATION_JSON)
                .get(StopPointsWithin.class);
        return response;
    }

    public static void main(String args[]) {

        PostCodeInfo postcodeInfo = PostCodeInfo.getPostCodeInfo("NW3 4BJ");
        System.out.println(postcodeInfo);
        StopPointsWithin stops = getStopPointsWithin(postcodeInfo.result.latitude, postcodeInfo.result.longitude);

        for (StopPointsWithinInfo info : stops.stopPoints) {
            System.out.println(info);
        }

    }

}

