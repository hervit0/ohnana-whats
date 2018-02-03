package ohnana.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ohnana.configuration.SwaggerStaticContent;
import ohnana.model.generic.AttributeInterface;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game implements AttributeInterface<Game> {
    @Id
    @Column(name = "game_id")
    @ApiModelProperty(notes = SwaggerStaticContent.GAME_ID)
    @JsonIgnore
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    @ApiModelProperty(notes = SwaggerStaticContent.GAME_SESSION)
    @JsonBackReference
    private Session session;

    @Override
    public String toString() {
        return "Game";
    }
}
