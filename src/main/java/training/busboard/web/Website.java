package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.BusArrival;
import training.busboard.PostCodeInfo;
import training.busboard.StopPointsWithin;

import java.util.Arrays;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        PostCodeInfo postcodeInfo = PostCodeInfo.getPostCodeInfo(postcode);
        StopPointsWithin stopPointsWithin = StopPointsWithin.getStopPointsWithin(
                postcodeInfo.result.latitude,
                postcodeInfo.result.longitude);

        return new ModelAndView("info", "busInfo", new BusInfo(postcode)) ;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}