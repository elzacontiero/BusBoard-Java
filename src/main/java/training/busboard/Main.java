package training.busboard;


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

