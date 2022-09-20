package training.busboard;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;

public class Main {

    // Part 1 - Get bus arrivals for StopPoint 490008660N.

   public static void part1() {
        String tflEndpoint = "https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals";
//        Client client = ClientBuilder.newClient();
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String response = client.target(tflEndpoint)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

       System.out.println(response);

    }


    public static void main(String args[]) {
      part1();
    }
}
