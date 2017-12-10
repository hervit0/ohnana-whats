package ohnana.controller;

import ohnana.mapper.SessionMapper;
import ohnana.model.Session;
import ohnana.model.SessionApiRequest;
import ohnana.model.generic.ApiResponse;
import ohnana.persistence.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SessionController extends BaseController {
    @Autowired
    public SessionRepository sessionRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/session")
    public ApiResponse<Session> create(@RequestBody SessionApiRequest request) {
        Session session = SessionMapper.map(request);
        sessionRepository.save(session);
        return session.createSessionApiResponse();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/session/{sessionId}")
    public ApiResponse<Session> get(@RequestParam Long sessionId) {
        Session session = sessionRepository.findOne(sessionId);
        return session.createSessionApiResponse();
    }
}

