package ohnana.model.generic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ohnana.model.Session;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class ApiResponse<T extends AttributeInterface<T>> {
    private ApiData<T> data;

    private List<ApiError> errors;

    public static <T extends AttributeInterface<T>> ApiResponse<T> createApiResponse(T data) {
        return ApiResponse.<T>builder()
                .data(ApiData.createData(data))
                .build();
    }

    public static <T extends AttributeInterface<T>> ApiResponse<T> throwUnauthorized() {
        ApiError unauthorizedError = ApiError.builder()
                .status("401")
                .title("Failed to pass authorization level")
                .build();

        return ApiResponse.<T>builder()
                .errors(Collections.singletonList(unauthorizedError))
                .build();
    }
}
