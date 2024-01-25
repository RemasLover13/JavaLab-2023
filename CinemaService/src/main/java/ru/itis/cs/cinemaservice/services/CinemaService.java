package ru.itis.cs.cinemaservice.services;

import ru.itis.cs.cinemaservice.dto.CinemaDto;
import ru.itis.cs.cinemaservice.dto.CinemasPage;
import ru.itis.cs.cinemaservice.dto.NewCinemaDto;

public interface CinemaService {

    CinemaDto getCinema(Long cinemaId);

    CinemaDto getCinema(String title);

    CinemaDto addCinema(NewCinemaDto newCinemaDto);

    CinemasPage getCinema(Integer page);

    CinemasPage getCinema(Integer page, String city);
}
