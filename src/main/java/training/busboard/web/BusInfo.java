package training.busboard.web;

import java.util.HashMap;

public class BusInfo {
    private final String postcode;


    public BusInfo(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }
}
