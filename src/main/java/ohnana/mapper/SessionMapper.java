package ohnana.mapper;

import ohnana.model.Session;
import ohnana.model.SessionApiRequest;
import ohnana.model.generic.ApiResponse;
import ohnana.service.SessionService;

public class SessionMapper {
    public static ApiResponse<Session> map(SessionApiRequest request) {
        Session session = Session.builder()
                .players(request.getPlayers())
                .text(SessionService.getTimeText())
                .build();

        return session.createSessionApiResponse();
    }
}
