package ohnana.controller;

import ohnana.model.Session;
import ohnana.model.generic.ApiResponse;
import ohnana.persistence.SessionRepository;
import ohnana.service.AuthorizationChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SessionController extends BaseController {
    @Autowired
    public SessionRepository sessionRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/session", produces = "application/json")
    public ResponseEntity<ApiResponse<Session>> create(@RequestHeader("Authorization") String authorization) {
        if (AuthorizationChecker.validate(authorization)) {
            return unauthorizedResponse();
        }

        Session session = Session.builder()
                .id(UUID.randomUUID())
                .token(authorization)
                .build();

        sessionRepository.save(session);
        return createdResponse(session);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/session", produces = "application/json")
    public ResponseEntity<ApiResponse<Session>> get(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Session-Id") UUID sessionId
    ) {
        if (AuthorizationChecker.validate(authorization)) {
            return unauthorizedResponse();
        }

        Session session = sessionRepository.findOne(sessionId);
        return okResponse(session);
    }

    private ResponseEntity<ApiResponse<Session>> unauthorizedResponse() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.throwUnauthorized());
    }

    private ResponseEntity<ApiResponse<Session>> createdResponse(Session session) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.createApiResponse(session));
    }

    private ResponseEntity<ApiResponse<Session>> okResponse(Session session) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.createApiResponse(session));
    }
}
