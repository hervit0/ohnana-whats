package ohnana.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "player_id")
    private Long id;

    private String name;

    private int order;

    private int team;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id")
    private Session session;
}
