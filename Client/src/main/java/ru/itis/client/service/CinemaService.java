package ru.itis.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.client.dto.CinemasPage;

@FeignClient(name = "cinemas-service", url = "${feign.cinemas-service.url}")
public interface CinemaService {

    @GetMapping(value = "/api/cinemas/city", consumes = MediaType.APPLICATION_JSON_VALUE)
    CinemasPage getCinemas(@RequestParam("api_key") String apiKey,
                           @RequestParam("page") Integer page,
                           @RequestParam("city") String city);
}
