package ru.itis.hs.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.hs.dto.HotelDto;
import ru.itis.hs.dto.HotelsPage;
import ru.itis.hs.dto.NewHotelDto;
import ru.itis.hs.exceptions.RestException;
import ru.itis.hs.models.Hotel;
import ru.itis.hs.repositories.HotelRepository;
import ru.itis.hs.services.HotelService;

import java.util.Optional;

import static ru.itis.hs.dto.HotelDto.from;
import static ru.itis.hs.util.PageUtils.DEFAULT_PAGE_SIZE;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    HotelRepository hotelRepository;

    @Override
    public HotelDto getHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Hotel with id {" + hotelId + "} not found"));

        return from(hotel);
    }

    @Override
    public HotelDto getHotel(String title) {
        Optional<Hotel> hotel = hotelRepository.findByTitle(title);

        return hotel.map(HotelDto::from).orElse(null);
    }

    @Transactional
    @Override
    public HotelDto addHotel(NewHotelDto newHotelDto) {
        HotelDto hotelDto = this.getHotel(newHotelDto.getTitle());
        if (hotelDto != null) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Hotel with title {" + newHotelDto.getTitle() + "} already exists");
        }

        Hotel hotel = Hotel.builder()
                .title(newHotelDto.getTitle())
                .city(newHotelDto.getCity())
                .address(newHotelDto.getAddress())
                .rooms(newHotelDto.getRooms())
                .stars(newHotelDto.getStars())
                .build();

        hotelRepository.save(hotel);

        return from(hotel);
    }

    @Override
    public HotelsPage getHotel(Integer page) {
        PageRequest request = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id"));

        Page<Hotel> hotels = hotelRepository.findAll(request);

        return HotelsPage.builder()
                .hotels(from(hotels.getContent()))
                .totalPages(hotels.getTotalPages())
                .build();
    }

    @Override
    public HotelsPage getHotel(Integer page, String city) {
        PageRequest pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id"));

        Page<Hotel> hotels = hotelRepository.findAllByCity(pageRequest,city);
        return HotelsPage.builder()
                .hotels(from(hotels.getContent()))
                .totalPages(hotels.getTotalPages())
                .build();
    }
}
