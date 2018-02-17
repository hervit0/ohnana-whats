package ohnana.mapper;

import lombok.Data;
import ohnana.model.Game;
import ohnana.model.GameApiRequest;
import ohnana.model.Session;
import ohnana.persistence.CardRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class GameMapper {
    public static Game map(
            GameApiRequest request,
            Session session,
            CardRepository cardRepository
    ) {
        Game game = Game.builder()
                .id(UUID.randomUUID())
                .session(session)
                .build();

        cardRepository.getRandomCards()
                .forEach(game::addCard);

        session.addGame(game);

        return game;
    }
}
