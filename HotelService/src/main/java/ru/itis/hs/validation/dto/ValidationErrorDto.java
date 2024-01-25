package ru.itis.hs.validation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ValidationError", description = "Error of validation")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ValidationErrorDto {

    @Schema(description = "The field where the exception occurred")
    private String field;

    @Schema(description = "Error message", example = "title must not be null")
    private String message;

    @Schema(description = "Which value was received from the client", example = "null")
    private String rejectedValue;
}
