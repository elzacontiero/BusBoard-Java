package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

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
