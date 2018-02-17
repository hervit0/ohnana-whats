package ohnana.controller;

import ohnana.factory.GameFactory;
import ohnana.factory.SessionFactory;
import ohnana.model.Game;
import ohnana.model.Session;
import ohnana.model.generic.ApiError;
import ohnana.model.generic.ApiResponse;
import ohnana.persistence.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class SessionControllerTest {
    @InjectMocks
    private SessionController subject = new SessionController();

    @Mock
    SessionRepository sessionRepository;

    private static UUID sessionId = UUID.randomUUID();
    private static Session session = SessionFactory.create(sessionId);
    private static String correctAuthorization = null;
    private static String incorrectAuthorization = "Wrong token";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Session mockSession = SessionFactory.createDefault();
        when(sessionRepository.save(any(Session.class))).thenReturn(mockSession);
    }

    @Test
    @DisplayName(".create - returns an ApiResponse with 201 Created")
    public void create_givenCorrectAuthorization_returnsCreatedResponse() {
        // Act
        ResponseEntity<ApiResponse<Session>> response = subject.create(correctAuthorization);

        // Assert
        assertEquals("201", response.getStatusCode().toString());

        ApiResponse<Session> responseBody = response.getBody();
        assertEquals("Session", responseBody.getData().getType());
        assertNotNull(responseBody.getData().getId());
    }

    @Test
    @DisplayName(".create - returns an ApiResponse with 401 Unauthorized")
    public void create_givenIncorrectAuthorization_returnsUnauthorized() {
        // Act
        ResponseEntity<ApiResponse<Session>> response = subject.create(incorrectAuthorization);

        // Assert
        assertEquals("401", response.getStatusCode().toString());

        ApiError apiError = response.getBody().getErrors().get(0);
        assertEquals("401", apiError.getStatus());
        assertEquals("Failed to pass authorization level", apiError.getTitle());
    }

    @Test
    @DisplayName(".get - returns an ApiResponse with 200 OK")
    public void get_givenCorrectAuthorization_returnsOkResponse() {
        // Arrange
        List<Game> games = GameFactory.createMultiple(3);
        Session session = SessionFactory.create(sessionId, games);
        when(sessionRepository.findOne(sessionId)).thenReturn(session);

        // Act
        ResponseEntity<ApiResponse<Session>> response = subject.get(correctAuthorization, sessionId);

        // Assert
        assertEquals("200", response.getStatusCode().toString());

        ApiResponse<Session> responseBody = response.getBody();
        assertEquals("Session", responseBody.getData().getType());
        assertEquals(sessionId, responseBody.getData().getId());
        assertEquals(3, responseBody.getData().getAttributes().getGames().size());
    }

    @Test
    @DisplayName(".get - returns an ApiResponse with 401 Unauthorized")
    public void get_givenIncorrectAuthorization_returnsUnauthorized() {
        // Arrange
        when(sessionRepository.findOne(sessionId)).thenReturn(session);

        // Act
        ResponseEntity<ApiResponse<Session>> response = subject.get(incorrectAuthorization, sessionId);

        // Assert
        assertEquals("401", response.getStatusCode().toString());

        ApiError apiError = response.getBody().getErrors().get(0);
        assertEquals("401", apiError.getStatus());
        assertEquals("Failed to pass authorization level", apiError.getTitle());
    }

    @Test
    @DisplayName(".get - returns an ApiResponse with 404 Not Found")
    public void get_givenUnexistingRecord_returnsNotFound() {
        // Arrange
        when(sessionRepository.findOne(sessionId)).thenReturn(null);

        // Act
        ResponseEntity<ApiResponse<Session>> response = subject.get(correctAuthorization, sessionId);

        // Assert
        assertEquals("404", response.getStatusCode().toString());

        ApiError apiError = response.getBody().getErrors().get(0);
        assertEquals("404", apiError.getStatus());
        assertEquals("Session not found", apiError.getTitle());
    }
}
