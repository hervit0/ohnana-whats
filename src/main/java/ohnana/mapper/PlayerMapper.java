package ohnana.mapper;

import lombok.Data;
import ohnana.model.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class PlayerMapper {
    public static List<Player> map(List<Player> requestPlayers) {
        return requestPlayers.stream()
                .map(PlayerMapper::map)
                .collect(Collectors.toList());
    }

    private static Player map(Player requestPlayer) {
        return Player.builder()
                .name(requestPlayer.getName())
                .team(requestPlayer.getTeam())
                .order(requestPlayer.getOrder())
                .build();
    }
}
