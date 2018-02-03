package ohnana.persistence.seeder;

import ohnana.model.Card;
import ohnana.persistence.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Seeder {
    private CardRepository cardRepository;

    @Autowired
    public Seeder(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedCardsTable();
    }

    private void seedCardsTable() {
        Card card = Card.builder()
                .id(UUID .randomUUID())
                .name("Seeded from Java")
                .build();
        cardRepository.save(card);
    }
}
