package ohnana.controller;

import ohnana.factory.CardFactory;
import ohnana.factory.GameApiRequestFactory;
import ohnana.factory.GameFactory;
import ohnana.factory.SessionFactory;
import ohnana.model.Game;
import ohnana.model.GameApiRequest;
import ohnana.model.Session;
import ohnana.model.generic.ApiResponse;
import ohnana.persistence.CardRepository;
import ohnana.persistence.GameRepository;
import ohnana.persistence.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class GameControllerTest {
    @InjectMocks
    private GameController subject = new GameController();

    @Mock
    GameRepository gameRepository;

    @Mock
    SessionRepository sessionRepository;

    @Mock
    CardRepository cardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Game mockGame = GameFactory.createDefault();
        Session mockSession = SessionFactory.createDefault();
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);
        when(sessionRepository.findOne(UUID.fromString("18003be5-092d-4f9a-827d-67295d5a9e83")))
                .thenReturn(mockSession);
        when(cardRepository.getRandomCards()).thenReturn(CardFactory.createMultiple());
    }

    @Test
    @DisplayName(".create - empty request triggers error")
    public void create_givenEmptyRequest_returnsError() {
        // TODO
        // protection layer for the mapper - mapper should never see a null
    }

    @Test
    @DisplayName(".create - invalid request triggers error")
    public void create_givenEmptyPlayers_returnsError() {
        // TODO
    }

    @Test
    @DisplayName(".create - invalid numbers of players triggers error")
    public void create_givenInvalidNumberOfPlayers_returnsError() {
        // TODO
        // Business logic: 4 players, gatekeeper should be implement later to keep development easy
    }

    @Test
    @DisplayName(".create - returns an ApiResponse following JSON standards structure")
    public void create_givenRequest_returnsJsonStandardsStructure() {
        // Arrange
        Optional<GameApiRequest> request = Optional.of(GameApiRequestFactory.createDefault());

        // Act
        ApiResponse<Game> response = subject.create(request);

        // Assert
        assertEquals("Game", response.getData().getType());
        assertNotNull(response.getData().getId());
    }
}
