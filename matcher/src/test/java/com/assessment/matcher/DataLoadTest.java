package com.assessment.matcher;

import com.assessment.matcher.repository.CuisineRepository;
import com.assessment.matcher.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DataLoadTest {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void shouldLoadExpectedCsvData() {
        assertThat(cuisineRepository.count()).isEqualTo(19);
        assertThat(restaurantRepository.count()).isEqualTo(200);
    }
}

