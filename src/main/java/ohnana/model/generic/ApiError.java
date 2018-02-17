package ohnana.model.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ApiError {
    private String status;
    private String title;
}
