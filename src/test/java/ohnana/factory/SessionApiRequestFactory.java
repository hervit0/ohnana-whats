package ohnana.factory;

import ohnana.model.Player;
import ohnana.model.SessionApiRequest;

import java.util.Collections;
import java.util.List;

public class SessionApiRequestFactory {
    public static SessionApiRequest createDefault() {
       return SessionApiRequest.builder()
               .players(PlayerFactory.createMultiple(1))
               .build();
    }

    public static SessionApiRequest create(List<Player> players) {
       return SessionApiRequest.builder()
               .players(players)
               .build();
    }

    public static SessionApiRequest create(Player player) {
        return SessionApiRequestFactory.create(Collections.singletonList(player));
    }
}
