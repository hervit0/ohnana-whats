package ohnana.controller;

import ohnana.factory.CardFactory;
import ohnana.factory.GameApiRequestFactory;
import ohnana.factory.GameFactory;
import ohnana.factory.SessionFactory;
import ohnana.model.Game;
import ohnana.model.GameApiRequest;
import ohnana.model.Session;
import ohnana.model.generic.ApiError;
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
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class GameControllerTest {
    @InjectMocks
    private GameController subject = new GameController();

    private static UUID gameId = UUID.randomUUID();
    private static UUID sessionId = UUID.randomUUID();

    private static Game game = GameFactory.create(gameId);
    private static Session session = SessionFactory.createDefault();
    private static Optional<GameApiRequest> request = Optional.of(GameApiRequestFactory.createDefault());

    private static String correctAuthorization = null;
    private static String incorrectAuthorization = "Wrong token";

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
        when(gameRepository.save(any(Game.class))).thenReturn(mockGame);
        when(cardRepository.getRandomCards()).thenReturn(CardFactory.createMultiple());
    }

    @Test
    @DisplayName(".create - returns an ApiResponse with 200 OK")
    public void create_givenCorrectAuthorization_returnsOkResponse() {
        // Arrange
        when(sessionRepository.findOne(sessionId)).thenReturn(session);

        // Act
        ResponseEntity<ApiResponse<Game>> response = subject.create(request, correctAuthorization, sessionId);

        // Assert
        assertEquals("201", response.getStatusCode().toString());

        ApiResponse<Game> responseBody = response.getBody();
        assertEquals("Game", responseBody.getData().getType());
        assertNotNull(responseBody.getData().getId());
    }

    @Test
    @DisplayName(".create - returns an ApiResponse with 401 Unauthorized")
    public void create_givenIncorrectAuthorization_returnsUnauthorized() {
        // Act
        ResponseEntity<ApiResponse<Game>> response = subject.create(request, incorrectAuthorization, sessionId);

        // Assert
        assertEquals("401", response.getStatusCode().toString());

        ApiError apiError = response.getBody().getErrors().get(0);
        assertEquals("401", apiError.getStatus());
        assertEquals("Failed to pass authorization level", apiError.getTitle());
    }

    @Test
    @DisplayName(".create - returns an ApiResponse with 404 Not Found")
    public void create_givenUnexistingRecord_returnsNotFound() {
        // Arrange
        when(sessionRepository.findOne(sessionId)).thenReturn(null);

        // Act
        ResponseEntity<ApiResponse<Game>> response = subject.create(request, correctAuthorization, sessionId);

        // Assert
        assertEquals("404", response.getStatusCode().toString());

        ApiError apiError = response.getBody().getErrors().get(0);
        assertEquals("404", apiError.getStatus());
        assertEquals("Session not found", apiError.getTitle());
    }

    @Test
    @DisplayName(".get - returns an ApiResponse with 200 OK")
    public void get_givenValidRequest_returnsOkResponse() {
        // Arrange
        when(sessionRepository.findOne(sessionId)).thenReturn(session);
        when(gameRepository.findOne(gameId)).thenReturn(game);

        // Act
        ResponseEntity<ApiResponse<Game>> response = subject.get(gameId, correctAuthorization, sessionId);

        // Assert
        assertEquals("200", response.getStatusCode().toString());

        ApiResponse<Game> responseBody = response.getBody();
        assertEquals("Game", responseBody.getData().getType());
        assertEquals(gameId, responseBody.getData().getId());
    }

    @Test
    @DisplayName(".get - returns an ApiResponse with 401 Unauthorized")
    public void get_givenIncorrectAuthorization_returnsUnauthorized() {
        // Act
        ResponseEntity<ApiResponse<Game>> response = subject.get(gameId, incorrectAuthorization, sessionId);

        // Assert
        assertEquals("401", response.getStatusCode().toString());

        ApiError apiError = response.getBody().getErrors().get(0);
        assertEquals("401", apiError.getStatus());
        assertEquals("Failed to pass authorization level", apiError.getTitle());
    }

    @Test
    @DisplayName(".get - returns an ApiResponse with 404 Not Found")
    public void get_givenUnexistingSession_returnsNotFound() {
        // Arrange
        when(sessionRepository.findOne(sessionId)).thenReturn(null);

        // Act
        ResponseEntity<ApiResponse<Game>> response = subject.get(gameId, correctAuthorization, sessionId);

        // Assert
        assertEquals("404", response.getStatusCode().toString());

        ApiError apiError = response.getBody().getErrors().get(0);
        assertEquals("404", apiError.getStatus());
        assertEquals("Session not found", apiError.getTitle());
    }

    @Test
    @DisplayName(".get - returns an ApiResponse with 404 Not Found")
    public void get_givenUnexistingGame_returnsNotFound() {
        // Arrange
        when(sessionRepository.findOne(sessionId)).thenReturn(session);
        when(gameRepository.findOne(gameId)).thenReturn(null);

        // Act
        ResponseEntity<ApiResponse<Game>> response = subject.get(gameId, correctAuthorization, sessionId);

        // Assert
        assertEquals("404", response.getStatusCode().toString());

        ApiError apiError = response.getBody().getErrors().get(0);
        assertEquals("404", apiError.getStatus());
        assertEquals("Game not found", apiError.getTitle());
    }
}
