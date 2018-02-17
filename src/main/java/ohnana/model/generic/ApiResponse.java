package ohnana.model.generic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

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

    public static <T extends AttributeInterface<T>> ApiResponse<T> throwNotFound(String resource) {
        ApiError unauthorizedError = ApiError.builder()
                .status("404")
                .title(String.format("%s not found", resource))
                .build();

        return ApiResponse.<T>builder()
                .errors(Collections.singletonList(unauthorizedError))
                .build();
    }
}
