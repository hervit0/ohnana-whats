package ohnana.mapper;

import ohnana.model.Player;

import java.util.List;
import java.util.stream.Collectors;

import static ohnana.controller.SessionController.globalCounter;

public class PlayerMapper {
    public static List<Player> map(List<Player> requestPlayers) {
        return requestPlayers.stream()
                .map(PlayerMapper::map)
                .collect(Collectors.toList());
    }

    private static Player map(Player requestPlayer) {
        return Player.builder()
                .id((int)globalCounter.getAndIncrement())
                .name(requestPlayer.getName())
                .team(requestPlayer.getTeam())
                .order(requestPlayer.getOrder())
                .build();
    }
}
