package ru.itis.client.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.client.dto.CinemasPage;
import ru.itis.client.dto.CityPage;
import ru.itis.client.dto.HotelsPage;
import ru.itis.client.service.CinemaService;
import ru.itis.client.service.HotelService;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class GeneralController {

    HotelService hotelService;
    CinemaService cinemaService;

    @GetMapping("/info")
    public ResponseEntity<CityPage> getInfo(@RequestParam("city") String city,
                                            @RequestParam("cinemas_page") Integer cinemasPageNumber,
                                            @RequestParam("hotels_page") Integer hotelsPageNumber) {
        HotelsPage hotelsPage = hotelService.getHotels("hotels_key", hotelsPageNumber, city);
        CinemasPage cinemasPage = cinemaService.getCinemas("cinemas_key", cinemasPageNumber, city);

        return ResponseEntity.ok(CityPage.builder()
                .cinemasPage(cinemasPage)
                .hotelsPage(hotelsPage)
                .build());
    }
}
