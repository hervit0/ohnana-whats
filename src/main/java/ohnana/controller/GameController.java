package ohnana.controller;

import ohnana.mapper.GameMapper;
import ohnana.model.Game;
import ohnana.model.GameApiRequest;
import ohnana.model.Session;
import ohnana.model.generic.ApiResponse;
import ohnana.persistence.CardRepository;
import ohnana.persistence.GameRepository;
import ohnana.persistence.SessionRepository;
import ohnana.service.AuthorizationChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class GameController extends BaseController {
    @Autowired
    public GameRepository gameRepository;

    @Autowired
    public SessionRepository sessionRepository;

    @Autowired
    public CardRepository cardRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/game", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ApiResponse<Game>> create(
            @RequestBody Optional<GameApiRequest> request,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Session-Id") UUID sessionId
    ) {
        Session session = sessionRepository.findOne(sessionId);

        if (AuthorizationChecker.validate(authorization)) {
            return ApiResponse.unauthorizedResponse();
        } else if (session == null) {
            return ApiResponse.notFoundResponse("Session");
        }

        Game game = GameMapper.map(request.orElse(null), session, cardRepository);
        gameRepository.save(game);
        return ApiResponse.createdResponse(game);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/game/{gameId}", produces = "application/json")
    public ResponseEntity<ApiResponse<Game>> get(
            @PathVariable("gameId") UUID gameId,
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Session-Id") UUID sessionId
    ) {
        Session session = sessionRepository.findOne(sessionId);
        Game game = gameRepository.findOne(gameId);

        if (AuthorizationChecker.validate(authorization)) {
            return ApiResponse.unauthorizedResponse();
        } else if (session == null) {
            return ApiResponse.notFoundResponse("Session");
        } else if (game == null) {
            return ApiResponse.notFoundResponse("Game");
        }

        return ApiResponse.okResponse(game);
    }
}
