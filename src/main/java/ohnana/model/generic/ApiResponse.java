package ohnana.model.generic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
@Builder
public class ApiResponse<T extends AttributeInterface<T>> {
    private ApiData<T> data;

    public static <T extends AttributeInterface<T>> ApiResponse<T> createApiResponse(T data) {
        return ApiResponse.<T>builder()
                .data(ApiData.createData(data))
                .build();
    }
}
