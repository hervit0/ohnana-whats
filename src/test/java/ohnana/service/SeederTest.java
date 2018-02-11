package ohnana.service;

import ohnana.factory.CardFactory;
import ohnana.model.Card;
import ohnana.persistence.CardRepository;
import ohnana.persistence.seeder.Parser;
import ohnana.persistence.seeder.Seeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SeederTest {
    @Mock
    CardRepository cardRepository;

    @Mock
    Parser parser;

    @InjectMocks
    private Seeder subject = new Seeder(cardRepository);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(parser.getCards()).thenReturn(CardFactory.createMultiple());
        when(cardRepository.checkCardPresence("name_1")).thenReturn(false);
        when(cardRepository.checkCardPresence("name_2")).thenReturn(true);
    }

    @Test
    @DisplayName(".seedCardsTable - Save cards not already stored")
    public void seedCardsTable() {
        subject.seedCardsTable();

        ArgumentCaptor<Card> argumentCaptor = ArgumentCaptor.forClass(Card.class);
        verify(cardRepository).save(argumentCaptor.capture());
        Card capturedCard = argumentCaptor.<Card>getValue();
        assertEquals(capturedCard.getName(), "name_1");
    }
}
