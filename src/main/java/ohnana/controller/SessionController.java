package ohnana.controller;

import ohnana.mapper.PlayerMapper;
import ohnana.mapper.SessionMapper;
import ohnana.model.Session;
import ohnana.model.SessionApiRequest;
import ohnana.model.generic.ApiResponse;
import ohnana.persistence.PlayerRepository;
import ohnana.persistence.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
//@EnableJpaRepositories(basePackages = "ohnana.persistence")
//@EnableAutoConfiguration
public class SessionController extends BaseController {
    public static final AtomicLong globalCounter = new AtomicLong();

    @Autowired
    public SessionRepository sessionRepository;
    @Autowired
    public PlayerRepository playerRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/session")
    public ApiResponse<Session> create(@RequestBody SessionApiRequest request) {
        PlayerMapper playerMapper = new PlayerMapper(playerRepository);
        SessionMapper sessionMapper = new SessionMapper(sessionRepository, playerMapper);

        return sessionMapper.map(request);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/session/{id}")
    public ApiResponse<Session> get(@RequestParam Long id) {
        PlayerMapper playerMapper = new PlayerMapper(playerRepository);
        SessionMapper sessionMapper = new SessionMapper(sessionRepository, playerMapper);

        return sessionMapper.get(id);
    }
}

