package ru.itis.hs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.hs.models.Hotel;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Hotel", description = "Отель")
public class HotelDto {
    @Schema(description = "Hotel ID", example = "1")
    private Long id;

    @Schema(description = "the name of the hotel", example = "Бета Измайлово", maxLength = 255)
    private String title;

    @Schema(description = "description of the hotel", example = "Измайловское ш., 71, корпус 2Б", maxLength = 500)
    private String address;

    @Schema(description = "the city where the hotel is located", example = "Moscow", maxLength = 255)
    private String city;

    @Schema(description = "number of rooms", example = "10")
    private int rooms;

    @Schema(description = "number of stars", example = "4")
    private int stars;

    public static HotelDto from(Hotel hotel) {
        return HotelDto.builder()
                .id(hotel.getId())
                .address(hotel.getAddress())
                .city(hotel.getCity())
                .rooms(hotel.getRooms())
                .stars(hotel.getStars())
                .title(hotel.getTitle())
                .build();
    }

    public static List<HotelDto> from(List<Hotel> hotelList) {
        return hotelList.stream()
                .map(HotelDto::from)
                .collect(Collectors.toList());
    }
}
