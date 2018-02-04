package ohnana.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ohnana.configuration.SwaggerStaticContent;
import ohnana.model.generic.AttributeInterface;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card")
public class Card implements AttributeInterface<Card> {
    @Id
    @Column(name = "card_id")
    @ApiModelProperty(notes = SwaggerStaticContent.CARD_ID)
    private UUID id;

    @Column(name = "name")
    @ApiModelProperty(notes = SwaggerStaticContent.CARD_NAME)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name = "game_cards",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    @ApiModelProperty(notes = SwaggerStaticContent.CARD_GAMES)
    private List<Game> games;

    @Override
    public String toString() {
        return "Card";
    }
}
