package ru.itis.hs.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hs.models.Hotel;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findByTitle(String title);

    Page<Hotel> findAllByCity(PageRequest request, String city);
}
