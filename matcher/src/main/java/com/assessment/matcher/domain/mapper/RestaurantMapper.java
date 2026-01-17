package com.assessment.matcher.domain.mapper;

import com.assessment.matcher.domain.dto.RestaurantDTO;
import com.assessment.matcher.domain.jpa.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public static RestaurantDTO toDTO(Restaurant e) {
        return new RestaurantDTO(
                e.getName(),
                e.getCuisine().getName(),
                e.getPrice(),
                e.getCustomerRating(),
                e.getDistance()
        );
    }
}
