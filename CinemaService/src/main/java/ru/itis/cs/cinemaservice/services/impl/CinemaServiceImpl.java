package ru.itis.cs.cinemaservice.services.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.cs.cinemaservice.dto.CinemaDto;
import ru.itis.cs.cinemaservice.dto.CinemasPage;
import ru.itis.cs.cinemaservice.dto.NewCinemaDto;
import ru.itis.cs.cinemaservice.exception.RestException;
import ru.itis.cs.cinemaservice.models.Cinema;
import ru.itis.cs.cinemaservice.repositories.CinemaRepository;
import ru.itis.cs.cinemaservice.services.CinemaService;

import java.util.Optional;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;
import static ru.itis.cs.cinemaservice.dto.CinemaDto.from;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    CinemaRepository cinemaRepository;

    @Override
    public CinemasPage getCinema(Integer page) {
        PageRequest request = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id"));

        Page<Cinema> cinemas = cinemaRepository.findAll(request);

        return CinemasPage.builder()
                .cinemas(from(cinemas.getContent()))
                .totalPages(cinemas.getTotalPages())
                .build();
    }

    @Override
    public CinemasPage getCinema(Integer page, String city) {
        PageRequest request = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id"));

        Page<Cinema> cinemas = cinemaRepository.findAllByCity(request, city);
        return CinemasPage.builder()
                .cinemas(from(cinemas.getContent()))
                .totalPages(cinemas.getTotalPages())
                .build();
    }

    @Override
    public CinemaDto getCinema(Long cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Cinema with id <" + cinemaId + "> not found"));
        return from(cinema);
    }

    @Override
    public CinemaDto getCinema(String title) {
        Optional<Cinema> cinema = cinemaRepository.findByTitle(title);

        return cinema.map(CinemaDto::from).orElse(null);
    }

    @Transactional
    @Override
    public CinemaDto addCinema(NewCinemaDto newCinema) {
        CinemaDto dto = this.getCinema(newCinema.getTitle());
        if (dto != null) {
            throw new RestException(HttpStatus.BAD_REQUEST, "Cinema with title <" + newCinema.getTitle() + "> already exists");
        }
        Cinema cinema = Cinema.builder()
                .title(newCinema.getTitle())
                .address(newCinema.getAddress())
                .city(newCinema.getCity())
                .build();

        cinemaRepository.save(cinema);

        return from(cinema);
    }

}
