package ohnana.controller;

import ohnana.model.Session;
import ohnana.model.generic.ApiResponse;
import ohnana.persistence.SessionRepository;
import ohnana.service.AuthorizationChecker;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ApiResponse.unauthorizedResponse();
        }

        Session session = Session.builder()
                .id(UUID.randomUUID())
                .token(authorization)
                .build();

        sessionRepository.save(session);
        return ApiResponse.createdResponse(session);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/session", produces = "application/json")
    public ResponseEntity<ApiResponse<Session>> get(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Session-Id") UUID sessionId
    ) {
        Session session = sessionRepository.findOne(sessionId);

        if (AuthorizationChecker.validate(authorization)) {
            return ApiResponse.unauthorizedResponse();
        } else if (session == null) {
            return ApiResponse.notFoundResponse("Session");
        }

        return ApiResponse.okResponse(session);
    }

}
