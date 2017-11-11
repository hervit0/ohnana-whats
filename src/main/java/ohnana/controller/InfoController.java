package ohnana.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class InfoController {

    @RequestMapping("/")
    public String index() {
        return "Welcome Ohnana";
    }

}

