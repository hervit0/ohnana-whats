package ohnana.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
public class InfoController extends BaseController {

    @RequestMapping(method = RequestMethod.GET, value = "/ohnana")
    public String index() {
        String time = ZonedDateTime.now(ZoneId.of("Europe/London")).toString();
        return String.format("Welcome Ohnana! - It's now %s", time);
    }

}

