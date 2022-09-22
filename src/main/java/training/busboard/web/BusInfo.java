package training.busboard.web;

public class BusInfo {
    private final String postcode;

    public BusInfo(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }
}
