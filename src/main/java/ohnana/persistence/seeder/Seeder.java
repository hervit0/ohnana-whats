package ohnana.persistence.seeder;

import ohnana.persistence.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Seeder {
    private CardRepository cardRepository;
    private Parser parser;

    @Autowired
    public Seeder(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        this.parser = new Parser();
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedCardsTable();
    }

    private void seedCardsTable() {
        parser.getCards().forEach(card -> cardRepository.save(card));
    }
}
