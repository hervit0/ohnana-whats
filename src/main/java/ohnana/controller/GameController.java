package ohnana.controller;

import ohnana.mapper.GameMapper;
import ohnana.model.Game;
import ohnana.model.GameApiRequest;
import ohnana.model.generic.ApiResponse;
import ohnana.persistence.GameRepository;
import ohnana.persistence.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class GameController extends BaseController {
    @Autowired
    public GameRepository gameRepository;

    @Autowired
    public SessionRepository sessionRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/game")
    public ApiResponse<Game> create(@RequestBody Optional<GameApiRequest> request) {
        Game game = GameMapper.map(request.orElse(null), sessionRepository);
        gameRepository.save(game);
        return ApiResponse.createApiResponse(game);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/game/{gameId}")
    public ApiResponse<Game> get(@PathVariable("gameId") UUID gameId) {
        Game game = gameRepository.findOne(gameId);
        return ApiResponse.createApiResponse(game);
    }
}
