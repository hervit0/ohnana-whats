package ohnana.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ohnana.model.generic.ApiResponse;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String text;
    private List<Player> players;

    public ApiResponse<Session> createSessionApiResponse() {
        return ApiResponse.createApiResponse(this);
    }
}
