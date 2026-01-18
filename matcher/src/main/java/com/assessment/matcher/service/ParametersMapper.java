package com.assessment.matcher.service;


import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.RestaurantDTO;
import com.assessment.matcher.domain.parameters.FilterLogic;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParametersMapper {

    public static Predicate<RestaurantDTO> getValidFilters (RequestDTO requestDTO) {

        Predicate<RestaurantDTO> predicate = r -> true;

        if (requestDTO.getRestaurantName() != null) {
            predicate = predicate.and(FilterLogic.NAME.build(requestDTO.getRestaurantName()));
        }

        if (requestDTO.getCuisineName() != null) {
            predicate = predicate.and(FilterLogic.CUISINE.build(requestDTO.getCuisineName()));
        }

        if (requestDTO.getPrice() != null) {
            predicate = predicate.and(FilterLogic.PRICE.build(requestDTO.getPrice()));
        }

        if (requestDTO.getCustomerRating() != null) {
            predicate = predicate.and(FilterLogic.CUSTOMER_RATING.build(requestDTO.getCustomerRating()));
        }

        if (requestDTO.getDistance() != null) {
            predicate = predicate.and(FilterLogic.DISTANCE.build(requestDTO.getDistance()));
        }

        return predicate;
    }
}
