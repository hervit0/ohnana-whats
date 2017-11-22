package ohnana.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Player {
    private String name;
    private int order;
    private int team;
}
