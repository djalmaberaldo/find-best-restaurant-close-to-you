package com.assessment.matcher.service;

import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.RestaurantDTO;
import com.assessment.matcher.repository.RestaurantRepository;
import org.hibernate.metamodel.mapping.Restrictable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MatcherServiceTest {

    private RestaurantRepository restaurantRepository;
    private MatcherService matcherService;

    @BeforeEach
    void setup() {
        restaurantRepository = Mockito.mock(RestaurantRepository.class);
        matcherService = new MatcherService(restaurantRepository);
    }

    @Test
    void shouldReturnEmptyList() {
        RequestDTO request = new RequestDTO();

        when(restaurantRepository.findAll()).thenReturn(List.of());

        List<RestaurantDTO> result = matcherService.findBestRestaurants(request);

        assertEquals(0, result.size());
    }
}
