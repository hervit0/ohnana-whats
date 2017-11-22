package ohnana.mapper;

import ohnana.factory.PlayerFactory;
import ohnana.factory.SessionApiRequestFactory;
import ohnana.model.Player;
import ohnana.model.Session;
import ohnana.model.SessionApiRequest;
import ohnana.model.SessionApiResponse;
import ohnana.model.generic.ApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static ohnana.factory.PlayerFactory.*;
import static org.junit.jupiter.api.Assertions.*;

public class SessionMapperTest {
    @Test
    @DisplayName("#map - returns players")
    public void map_whenCalled_returnsPlayers() {
        IntStream.of(0, 1, 3).forEach(this::assertNumberOfPlayers);
    }

    @Test
    @DisplayName("#map - returns players content")
    public void map_whenCalled_returnsPlayersContent() {
        // Arrange
        Player player = PlayerFactory.createDefault();
        SessionApiRequest request = SessionApiRequestFactory.create(player);

        // Act
        ApiResponse<Session> response = SessionMapper.map(request);

        // Assert
//        Player responsePlayer = response.getData().getAttributes().getPlayers().get(0);
//        assertEquals(DEFAULT_ID, responsePlayer.getId());
//        assertEquals(DEFAULT_NAME, responsePlayer.getName());
//        assertEquals(DEFAULT_ORDER, responsePlayer.getOrder());
//        assertEquals(DEFAULT_TEAM, responsePlayer.getTeam());
    }

    @Test
    @DisplayName("#map - returns an ApiResponse id")
    public void map_whenCalled_returnsApiResponseId() {
        // Arrange
        SessionApiRequest request = SessionApiRequestFactory.create();

        // Act
        ApiResponse<Session> response = SessionMapper.map(request);

        // Assert
        assertNotNull(response.getData().getId());
    }

    private void assertNumberOfPlayers(int numberOfPlayers) {
        // Arrange
        List<Player> players = PlayerFactory.createMultiple(numberOfPlayers);
        SessionApiRequest request = SessionApiRequestFactory.create(players);

        // Act
        ApiResponse<Session> response = SessionMapper.map(request);

        // Assert
//        assertEquals(numberOfPlayers, response.getData().getPlayers().size());
    }
}
