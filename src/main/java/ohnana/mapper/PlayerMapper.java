package ohnana.mapper;

import ohnana.model.Player;
import ohnana.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static ohnana.controller.SessionController.globalCounter;

@Component
public class PlayerMapper {
    private PlayerRepository playerRepository;

    public PlayerMapper(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> map(List<Player> requestPlayers) {
        return requestPlayers.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private Player map(Player requestPlayer) {
        Player player = Player.builder()
                .id(globalCounter.getAndIncrement())
                .name(requestPlayer.getName())
                .team(requestPlayer.getTeam())
                .order(requestPlayer.getOrder())
                .build();

        playerRepository.save(player);

        return player;
    }
}
