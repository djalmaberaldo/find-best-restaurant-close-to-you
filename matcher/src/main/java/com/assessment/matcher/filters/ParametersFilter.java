package com.assessment.matcher.filters;


import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.ResponseDTO;

import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import static com.assessment.matcher.filters.FilterLogic.*;

@Slf4j
public class ParametersFilter {

    public static Predicate<ResponseDTO> getValidFiltersFromRequest(RequestDTO requestDTO) {

        Predicate<ResponseDTO> predicate = r -> true;

        log.info("Building filters from request: {}", requestDTO);

        if (requestDTO.getRestaurantName() != null) {
            log.debug("Applying NAME filter with value={}", requestDTO.getRestaurantName());
            predicate = predicate.and(NAME.build(requestDTO.getRestaurantName()));
        }

        if (requestDTO.getCuisineName() != null) {
            log.debug("Applying CUISINE filter with value={}", requestDTO.getCuisineName());
            predicate = predicate.and(CUISINE.build(requestDTO.getCuisineName()));
        }

        if (requestDTO.getPrice() != null) {
            log.debug("Applying PRICE filter with value={}", requestDTO.getPrice());
            predicate = predicate.and(PRICE.build(requestDTO.getPrice()));
        }

        if (requestDTO.getCustomerRating() != null) {
            log.debug("Applying CUSTOMER_RATING filter with value={}", requestDTO.getCustomerRating());
            predicate = predicate.and(CUSTOMER_RATING.build(requestDTO.getCustomerRating()));
        }

        if (requestDTO.getDistance() != null) {
            log.debug("Applying DISTANCE filter with value={}", requestDTO.getDistance());
            predicate = predicate.and(DISTANCE.build(requestDTO.getDistance()));
        }

        log.debug("Finished building filters");

        return predicate;
    }


}
