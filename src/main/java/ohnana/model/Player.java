package ohnana.model;

import lombok.Builder;
import lombok.Data;
import ohnana.model.generic.AttributeInterface;

@Builder
@Data
public class Player implements AttributeInterface<Player> {
    private int id;
    private String name;
    private int order;
    private int team;
}
