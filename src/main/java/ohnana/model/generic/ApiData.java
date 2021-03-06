package ohnana.model.generic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
@Builder
public class ApiData<T extends AttributeInterface<T>> {
    private String type;
    private UUID id;
    private T attributes;

    public static <T extends AttributeInterface<T>> ApiData<T> createData(T classType) {
        Class<T> classTypeName = (Class<T>) classType.getClass();

        return ApiData.<T>builder()
                .type(classTypeName.getSimpleName())
                .id(classType.getId())
                .attributes(classType)
                .build();
    }
}
