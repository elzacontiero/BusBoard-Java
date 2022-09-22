package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointsWithinInfo {
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
