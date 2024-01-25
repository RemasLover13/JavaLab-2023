package ru.itis.client.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityPage {
    private CinemasPage cinemasPage;
    private HotelsPage hotelsPage;
}
