package ohnana.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ohnana.configuration.SwaggerStaticContent;
import ohnana.model.generic.AttributeInterface;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "session")
public class Session implements AttributeInterface<Session> {
    @Id
    @Column(name = "session_id")
    @ApiModelProperty(notes = SwaggerStaticContent.SESSION_ID)
    @JsonIgnore
    private UUID id;

    @ApiModelProperty(notes = SwaggerStaticContent.SESSION_GAMES)
    @OneToMany(
            mappedBy = "session",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    List<Game> games;

    @Column(name = "token")
    @ApiModelProperty(notes = SwaggerStaticContent.SESSION_TOKEN)
    private String token;

    public void addGame(Game game) {
        if (this.games == null){
            this.games = new ArrayList<Game>();
        }

        this.games.add(game);
    }

    @Override
    public String toString() {
        return "Session";
    }
}
