package ohnana.factory;

import ohnana.model.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerFactory {
//    public static final int DEFAULT_ID = 1;
    public static final String DEFAULT_NAME = "Celine Dion";
    public static final int DEFAULT_ORDER = 27;
    public static final int DEFAULT_TEAM = 1990;

    public static Player createDefault() {
        return Player.builder()
//                .id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .order(DEFAULT_ORDER)
                .team(DEFAULT_TEAM)
                .build();
    }

    public static Player create(int id) {
        return Player.builder()
//                .id(id)
                .build();
    }

    public static List<Player> createMultiple(int numberPlayers) {
        return IntStream.range(0, numberPlayers)
                .boxed()
                .map(PlayerFactory::create)
                .collect(Collectors.toList());
    }
}
