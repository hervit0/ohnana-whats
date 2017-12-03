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
@EnableJpaRepositories(basePackages = "ohnana.persistence")
@EnableAutoConfiguration
public class SessionController extends BaseController {
    public static final AtomicLong globalCounter = new AtomicLong();

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private PlayerRepository playerRepository;

    private PlayerMapper playerMapper = new PlayerMapper(playerRepository);
    private SessionMapper sessionMapper = new SessionMapper(sessionRepository, playerMapper);

    @RequestMapping(method = RequestMethod.POST, value = "/session")
    public ApiResponse<Session> create(@RequestBody SessionApiRequest request) {
        return sessionMapper.map(request);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/session/{id}")
    public ApiResponse<Session> get(@RequestParam Long id) {
        return sessionMapper.get(id);
    }
}

