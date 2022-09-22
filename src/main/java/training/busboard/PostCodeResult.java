package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostCodeResult {
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
