package ohnana.mapper;

import lombok.Data;
import ohnana.model.Game;
import ohnana.model.GameApiRequest;
import ohnana.model.Session;
import ohnana.persistence.SessionRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class GameMapper {
    public static Game map(GameApiRequest request, SessionRepository sessionRepository) {
//        Same session has been inserted in resources/schema.sql
        Session session = sessionRepository
                .findOne(UUID.fromString("18003be5-092d-4f9a-827d-67295d5a9e83"));

        Game game = Game.builder()
                .id(UUID.randomUUID())
                .session(session)
                .build();

        session.addGame(game);

        return game;
    }
}
