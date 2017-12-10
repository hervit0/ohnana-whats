package ohnana.mapper;

import lombok.Data;
import ohnana.model.Session;
import ohnana.model.SessionApiRequest;
import ohnana.service.SessionService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
public class SessionMapper {
    public static Session map(SessionApiRequest request) {
        Session session = Session.builder()
                .players(new ArrayList<>())
                .text(SessionService.getTimeText())
                .build();

        PlayerMapper.map(request.getPlayers()).forEach(session::bindPlayer);

        return session;
    }
}
