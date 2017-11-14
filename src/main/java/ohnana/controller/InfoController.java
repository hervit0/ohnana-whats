package ohnana.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
public class InfoController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index() {
        String time = ZonedDateTime.now(ZoneId.of("Europe/London")).toString();
        return String.format("Welcome Ohnana - %s", time);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test() {
        return "Back to the future - are we?";
    }

}

