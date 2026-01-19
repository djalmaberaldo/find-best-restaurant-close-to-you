package com.assessment.matcher.domain.mapper;

import com.assessment.matcher.domain.dto.ResponseDTO;
import com.assessment.matcher.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public static ResponseDTO toDTO(Restaurant e) {
        return new ResponseDTO(
                e.getName(),
                e.getCuisine().getName(),
                e.getPrice(),
                e.getCustomerRating(),
                e.getDistance()
        );
    }
}
