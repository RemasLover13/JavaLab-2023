package ru.itis.cs.cinemaservice.dto;


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
@Schema(description = "The page with cinemas")
public class CinemasPage {

    @Schema(description = "list of the cinemas")
    private List<CinemaDto> cinemas;

    @Schema(description = "Total number of pages with cinemas", example = "10")
    private Integer totalPages;


}
