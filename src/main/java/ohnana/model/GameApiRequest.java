package ohnana.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GameApiRequest {
    private List<Player> players;
}


