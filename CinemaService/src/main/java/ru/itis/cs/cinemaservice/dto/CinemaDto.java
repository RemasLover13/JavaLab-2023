package ru.itis.cs.cinemaservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.cs.cinemaservice.models.Cinema;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema
public class CinemaDto {
    @Schema(description = "идентификатор кинотеатра", example = "1")
    private Long id;

    @Schema(description = "название кинотеатра", example = "Серп и молот", maxLength = 255)
    private String title;

    @Schema(description = "описание кинотеатра", example = "улица Можайского, дом 37", maxLength = 500)
    private String address;

    @Schema(description = "город, в котором находится кинотеатр", example = "Смоленск", maxLength = 255)
    private String city;

    public static CinemaDto from(Cinema cinema) {

        return CinemaDto.builder()
                .id(cinema.getId())
                .title(cinema.getTitle())
                .address(cinema.getAddress())
                .city(cinema.getCity())
                .build();
    }

    public static List<CinemaDto> from(List<Cinema> tasks) {
        return tasks.stream()
                .map(CinemaDto::from)
                .collect(Collectors.toList());
    }

}
