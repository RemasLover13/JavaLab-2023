package ru.itis.hs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(name = "New Hotel", description = "The hotel to add")
public class NewHotelDto {
    @Schema(description = "name of the hotel", example = "", maxLength = 255)
    @NotEmpty
    @NotNull
    private String title;


    @Schema(description = "the city where the hotel is located", example = "Тюмень", maxLength = 255)
    @NotEmpty
    @NotNull
    private String city;

    @Schema(description = "address of the hotel", example = "улица Пушкина, дом 13", maxLength = 500, minLength = 5)
    @NotEmpty
    @NotNull
    private String address;


    @Schema(description = "numbers of rooms", example = "10")
    @NotNull
    @Min(value = 1, message = "Rooms must be bigger than 1")
    @Digits(integer = 3, fraction = 0)
    private int rooms;

    @Schema(description = "number of stars", example = "2")
    @NotNull
    @Min(value = 0, message = "Stars must be bigger than 0")
    @Max(value = 5, message = "Stars must be less than 5")
    @Digits(integer = 1, fraction = 0)
    private int stars;
}
