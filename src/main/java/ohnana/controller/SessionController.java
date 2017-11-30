package ohnana.controller;

import ohnana.mapper.SessionMapper;
import ohnana.model.Session;
import ohnana.model.SessionApiRequest;
import ohnana.model.generic.ApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SessionController extends BaseController {
    public static final AtomicLong globalCounter = new AtomicLong();

    @RequestMapping(method = RequestMethod.POST, value = "/session")
    public ApiResponse<Session> create(@RequestBody SessionApiRequest request) {
        return SessionMapper.map(request);
    }

}

