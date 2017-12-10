package ohnana.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ohnana.configuration.SwaggerStaticContent;
import ohnana.model.generic.AttributeInterface;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player implements AttributeInterface<Player> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    @ApiModelProperty(notes = SwaggerStaticContent.PLAYER_ID)
    private Long id;

    @ApiModelProperty(notes = SwaggerStaticContent.PLAYER_NAME)
    private String name;

    @Column(name = "player_order")
    @ApiModelProperty(notes = SwaggerStaticContent.PLAYER_ORDER)
    private int order;

    @ApiModelProperty(notes = SwaggerStaticContent.PLAYER_TEAM)
    private int team;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Player)) return false;
        return id != null && id.equals(((Player) object).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
