package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusArrival {

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

    public static List<BusArrival> getListOfBusArrival(String stopPoint) {
        String endpoint = "https://api.tfl.gov.uk/StopPoint/" + stopPoint + "/Arrivals";
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        List<BusArrival> response = client.target(endpoint)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<BusArrival>>() {
                });
        return response;
    }

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
