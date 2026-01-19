package com.assessment.matcher.filters;


import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.ResponseDTO;

import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParametersFilter {

    public static Predicate<ResponseDTO> getValidFiltersFromRequest (RequestDTO requestDTO) {

        Predicate<ResponseDTO> predicate = r -> true;

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
