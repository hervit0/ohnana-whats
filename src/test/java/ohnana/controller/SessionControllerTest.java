package ohnana.controller;

import ohnana.factory.PlayerFactory;
import ohnana.factory.SessionApiRequestFactory;
import ohnana.model.Player;
import ohnana.model.Session;
import ohnana.model.SessionApiRequest;
import ohnana.model.generic.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ohnana.factory.PlayerFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SessionControllerTest {
    private SessionController subject = new SessionController();

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
        SessionApiRequest request = SessionApiRequestFactory.createDefault();

        // Act
        ApiResponse<Session> response = subject.create(request);

        // Assert
        assertEquals("Session", response.getData().getType());
        assertNotNull(response.getData().getId());
    }

    @Test
    @DisplayName(".create - returns mapped players")
    public void create_givenPopulatedPlayers_returnsMappedResponse() {
        // Arrange
        Player player = PlayerFactory.createDefault();
        SessionApiRequest request = SessionApiRequestFactory.create(player);

        // Act
        ApiResponse<Session> response = subject.create(request);

        // Assert
        Player responsePlayer = response.getData().getAttributes().getPlayers().get(0);
        assertEquals(DEFAULT_NAME, responsePlayer.getName());
        assertEquals(DEFAULT_ORDER, responsePlayer.getOrder());
        assertEquals(DEFAULT_TEAM, responsePlayer.getTeam());
    }
}
