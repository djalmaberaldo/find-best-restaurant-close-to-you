package com.assessment.matcher.service;

import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.RestaurantDTO;
import com.assessment.matcher.domain.entity.Cuisine;
import com.assessment.matcher.domain.entity.Restaurant;
import com.assessment.matcher.filters.ParametersFilter;
import com.assessment.matcher.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MatcherServiceTest {

    private RestaurantRepository restaurantRepository;
    private ParametersFilter parametersFilter;
    private MatcherService matcherService;

    @BeforeEach
    void setup() {
        restaurantRepository = Mockito.mock(RestaurantRepository.class);
        parametersFilter = Mockito.mock(ParametersFilter.class);
        matcherService = new MatcherService(restaurantRepository, parametersFilter);
    }

    @Test
    void shouldReturnEmptyList() {

        when(restaurantRepository.findAll()).thenReturn(List.of());

        List<RestaurantDTO> result = matcherService.findBestRestaurants(RequestDTO.builder().build());

        assertEquals(0, result.size());
    }

    @Test
    void shouldSortByDistanceRatinPriceAndName() {
        var mockRequest = RequestDTO.builder()
                .restaurantName("Delicious")
                .build();
        Cuisine mockCuisine = mock(Cuisine.class);

        when(restaurantRepository.findAll()).thenReturn(List.of(
                new Restaurant(6L, "DeliciousE", 10, 3, 5, mockCuisine),
                new Restaurant(1L, "DeliciousD", 10, 3, 5, mockCuisine),
                new Restaurant(2L, "DeliciousC", 20, 4, 5, mockCuisine),
                new Restaurant(3L, "DeliciousB", 30, 4, 5, mockCuisine),
                new Restaurant(4L, "DeliciousA", 50, 3, 1, mockCuisine),
                new Restaurant(5L, "XXXXX", 10, 10, 5, mockCuisine)
        ));

        List<RestaurantDTO> result = matcherService.findBestRestaurants(mockRequest);

        assertEquals(5, result.size());
        assertEquals(1, result.get(0).getDistance());
        assertEquals(3, result.get(0).getCustomerRating());
        assertEquals("DeliciousA", result.get(0).getName());

        assertEquals(4, result.get(1).getCustomerRating());
        assertEquals(20, result.get(1).getPriceSinglePersonSpent());
        assertEquals("DeliciousC", result.get(1).getName());

        assertEquals(30, result.get(2).getPriceSinglePersonSpent());
        assertEquals("DeliciousB", result.get(2).getName());

        assertEquals(10, result.get(3).getPriceSinglePersonSpent());
        assertEquals("DeliciousD", result.get(3).getName());

        assertEquals(10, result.get(4).getPriceSinglePersonSpent());
        assertEquals("DeliciousE", result.get(4).getName());


    }
}
