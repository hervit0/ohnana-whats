package ohnana.factory;

import ohnana.model.Card;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardFactory {
    public static Card create(int increment) {
        return Card.builder()
                .name(String.format("name_%d", increment))
                .id(UUID.randomUUID())
                .build();
    }

    public static List<Card> createMultiple(int numberCards) {
        return IntStream.range(1, numberCards)
                .boxed()
                .map(CardFactory::create)
                .collect(Collectors.toList());
    }

    public static List<Card> createMultiple() {
        return createMultiple(2);
    }
}
