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
import org.springframework.http.HttpStatus;
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
            return unauthorizedResponse();
        } else if (session == null) {
            return notFound("Session");
        }

        Game game = GameMapper.map(request.orElse(null), session, cardRepository);
        gameRepository.save(game);
        return createdResponse(game);
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
            return unauthorizedResponse();
        } else if (session == null) {
            return notFound("Session");
        } else if (game == null) {
            return notFound("Game");
        }

        return okResponse(game);
    }

    private ResponseEntity<ApiResponse<Game>> unauthorizedResponse() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.throwUnauthorized());
    }

    private ResponseEntity<ApiResponse<Game>> notFound(String resource) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.throwNotFound(resource));
    }

    private ResponseEntity<ApiResponse<Game>> createdResponse(Game game) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.createApiResponse(game));
    }

    private ResponseEntity<ApiResponse<Game>> okResponse(Game game) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.createApiResponse(game));
    }
}
