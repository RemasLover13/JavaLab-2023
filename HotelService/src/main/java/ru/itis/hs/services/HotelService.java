package ru.itis.hs.services;

import ru.itis.hs.dto.HotelDto;
import ru.itis.hs.dto.HotelsPage;
import ru.itis.hs.dto.NewHotelDto;

public interface HotelService {

    HotelDto getHotel(Long hotelId);

    HotelDto getHotel(String tittle);

    HotelDto addHotel(NewHotelDto newHotelDto);

    HotelsPage getHotel(Integer page);

    HotelsPage getHotel(Integer page, String city);
}