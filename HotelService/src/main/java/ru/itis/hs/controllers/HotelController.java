package ru.itis.hs.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hs.controllers.api.HotelApi;
import ru.itis.hs.dto.HotelDto;
import ru.itis.hs.dto.HotelsPage;
import ru.itis.hs.dto.NewHotelDto;
import ru.itis.hs.models.Hotel;
import ru.itis.hs.services.HotelService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelController implements HotelApi {

    HotelService hotelService;

    @Override
    public ResponseEntity<HotelsPage> getHotel(Integer page) {
        return ResponseEntity.ok(hotelService.getHotel(page));
    }

    @Override
    public ResponseEntity<HotelsPage> getHotels(Integer page, String city) {
        return ResponseEntity.ok(hotelService.getHotel(page, city));
    }

    @Override
    public ResponseEntity<HotelDto> getHotel(Long hotelId) {
        return ResponseEntity.ok(hotelService.getHotel(hotelId));
    }

    @Override
    public ResponseEntity<HotelDto> addHotel(NewHotelDto newHotel) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(hotelService.addHotel(newHotel));
    }


}
