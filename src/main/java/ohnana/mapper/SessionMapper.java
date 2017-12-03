package ohnana.mapper;

import ohnana.model.Session;
import ohnana.model.SessionApiRequest;
import ohnana.model.generic.ApiResponse;
import ohnana.persistence.SessionRepository;
import ohnana.service.SessionService;
import org.springframework.stereotype.Component;

import static ohnana.controller.SessionController.globalCounter;

@Component
public class SessionMapper {
    private SessionRepository sessionRepository;
    private PlayerMapper playerMapper;

    public SessionMapper(
            SessionRepository sessionRepository,
            PlayerMapper playerMapper
    ) {
        this.sessionRepository = sessionRepository;
        this.playerMapper = playerMapper;
    }

    public ApiResponse<Session> map(SessionApiRequest request) {
        Session session = Session.builder()
                .id(globalCounter.getAndIncrement())
                .players(playerMapper.map(request.getPlayers()))
                .text(SessionService.getTimeText())
                .build();

        sessionRepository.save(session);

        return session.createSessionApiResponse();
    }

    public ApiResponse<Session> get(Long sessionId) {
        Session session = sessionRepository.findOne(sessionId);
        return session.createSessionApiResponse();
    }
}
