package ohnana.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ohnana.configuration.SwaggerStaticContent;
import ohnana.model.generic.ApiResponse;
import ohnana.model.generic.AttributeInterface;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "session")
public class Session implements AttributeInterface<Session> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    @ApiModelProperty(notes = SwaggerStaticContent.SESSION_ID)
    @JsonIgnore
    private Long id;

    private String text;

    @OneToMany(
            mappedBy = "session",
            cascade = CascadeType.ALL,
            fetch=FetchType.LAZY,
            orphanRemoval = true
    )
    @ApiModelProperty(notes = SwaggerStaticContent.SESSION_PLAYERS)
    private List<Player> players;

    public void bindPlayer(Player player) {
        // TODO: Remove this mutation
        players.add(player);
        player.setSession(this);
    }

    public ApiResponse<Session> createSessionApiResponse() {
        return ApiResponse.createApiResponse(this);
    }

    @Override
    public String toString() {
        return "Session";
    }
}
