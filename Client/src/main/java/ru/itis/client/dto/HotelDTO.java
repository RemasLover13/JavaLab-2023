package ru.itis.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDTO {
    private Long id;

    private String title;

    private String city;

    private String address;

    private int rooms;

    private int stars;
}
