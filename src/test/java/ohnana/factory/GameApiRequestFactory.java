package ohnana.factory;

import ohnana.model.Player;
import ohnana.model.GameApiRequest;

import java.util.Collections;
import java.util.List;

public class GameApiRequestFactory {
    public static GameApiRequest createDefault() {
       return GameApiRequest.builder()
               .players(PlayerFactory.createMultiple(1))
               .build();
    }

    public static GameApiRequest create(List<Player> players) {
       return GameApiRequest.builder()
               .players(players)
               .build();
    }

    public static GameApiRequest create(Player player) {
        return GameApiRequestFactory.create(Collections.singletonList(player));
    }
}
