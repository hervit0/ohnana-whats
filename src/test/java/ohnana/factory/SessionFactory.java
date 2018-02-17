package ohnana.factory;

import ohnana.model.Game;
import ohnana.model.Session;

import java.util.List;
import java.util.UUID;

public class SessionFactory {
    public static Session createDefault() {
      return Session.builder()
              .id(UUID.fromString("18003be5-092d-4f9a-827d-67295d5a9e83"))
              .build();
    }

    public static Session create(UUID uuid) {
        List<Game> games = GameFactory.createMultiple(2);

        return Session.builder()
                .id(uuid)
                .games(games)
                .build();
    }

    public static Session create(UUID uuid, List<Game> games) {
        return Session.builder()
                .id(uuid)
                .games(games)
                .build();
    }
}
