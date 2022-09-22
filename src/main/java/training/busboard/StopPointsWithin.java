package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointsWithin {

    public StopPointsWithinInfo[] stopPoints;

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

    @Override
    public String toString() {
        return "StopPointsWithin{" +
                "stopPoints=" + Arrays.toString(stopPoints) +
                '}';
    }
}
