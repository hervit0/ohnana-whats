package ohnana.factory;

import ohnana.model.Game;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameFactory {
    public static Game create(UUID gameId) {
        return Game.builder()
                .id(gameId)
                .build();
    }

    public static Game createDefault() {
        return Game.builder()
                .id(UUID.randomUUID())
                .build();
    }

    public static List<Game> createMultiple(int numberGames) {
        return IntStream.range(0, numberGames)
                .boxed()
                .map(x -> GameFactory.createDefault())
                .collect(Collectors.toList());
    }
}
