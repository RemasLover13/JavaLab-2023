package ru.itis.hs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "The page with hotels")
public class HotelsPage {
    @Schema(description = "list of the hotels")
    private List<HotelDto> hotels;

    @Schema(description = "Total number of pages with hotels", example = "10")
    private Integer totalPages;
}
