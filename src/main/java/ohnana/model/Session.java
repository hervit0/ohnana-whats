package ohnana.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ohnana.model.generic.ApiResponse;
import ohnana.model.generic.AttributeInterface;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "session")
public class Session implements AttributeInterface<Session> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long id;

    private String text;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Player> players;

    public ApiResponse<Session> createSessionApiResponse() {
        return ApiResponse.createApiResponse(this);
    }
}
