package ohnana.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
public class InfoController {

    @RequestMapping("/")
    public String index() {
        String time = ZonedDateTime.now(ZoneId.of("Europe/London")).toString();
        return String.format("Welcome Ohnana - %s", time);
    }

}

