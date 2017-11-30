package ohnana.mapper;

import ohnana.model.Session;
import ohnana.model.SessionApiRequest;
import ohnana.model.generic.ApiResponse;
import ohnana.service.SessionService;

import static ohnana.controller.SessionController.globalCounter;

public class SessionMapper {
    public static ApiResponse<Session> map(SessionApiRequest request) {
        Session session = Session.builder()
                .id((int)globalCounter.getAndIncrement())
                .players(PlayerMapper.map(request.getPlayers()))
                .text(SessionService.getTimeText())
                .build();

        return session.createSessionApiResponse();
    }
}
