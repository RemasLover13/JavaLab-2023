package ru.itis.hs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Message", description = "A message from the server. For example: errors and statuses")
public class StandardResponseDto {

    @Schema(description = "The text of the message")
    private String message;
}
