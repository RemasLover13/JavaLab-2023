package ru.itis.hs.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.hs.dto.HotelDto;
import ru.itis.hs.dto.HotelsPage;
import ru.itis.hs.dto.NewHotelDto;
import ru.itis.hs.dto.StandardResponseDto;
import ru.itis.hs.validation.dto.ValidationErrorsDto;

@Tags(value =
@Tag(name = "Hotels"))
@RequestMapping("/api/hotels")
public interface HotelApi {

    @Operation(summary = "Getting a list of hotels", description = "Available to all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request was processed successfully",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HotelsPage.class))
                    })
    }
    )
    @GetMapping
    public ResponseEntity<HotelsPage> getHotel(@Parameter(description = "Page number", example = "1") @RequestParam("page") Integer page);

    @Operation(summary = "Getting a list of hotels in the city", description = "Available to all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request was processed successfully",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HotelsPage.class))
                    })
    })
    @GetMapping("/city")
    ResponseEntity<HotelsPage> getHotels(@Parameter(description = "Page Number", example = "1")
                                          @RequestParam("page") Integer page,
                                          @RequestParam("city") String city);

    @Operation(summary = "Getting information about hotels", description = "Available to all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request was processed successfully",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "The hotel was not found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
                    })
    })
    @GetMapping("/{service-hotel-id}")
    ResponseEntity<HotelDto> getHotel(@Parameter(description = "Hotel ID", example = "1")
                                      @PathVariable("service-hotel-id") Long hotelId);

    @Operation(summary = "Adding a hotel", description = "Available only to managers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The task was added successfully",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
                    })
    })
    @PostMapping
    ResponseEntity<HotelDto> addHotel(@RequestBody @Valid NewHotelDto newHotel);

}
