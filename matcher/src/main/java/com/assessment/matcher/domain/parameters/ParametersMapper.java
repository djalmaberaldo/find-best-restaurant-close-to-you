package com.assessment.matcher.domain.parameters;


import com.assessment.matcher.domain.FilterLogic;
import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.RestaurantDTO;
import org.springframework.stereotype.Component;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ParametersMapper {

    public static Predicate<RestaurantDTO> getValidFilters (RequestDTO requestDTO) {

        Predicate<RestaurantDTO> predicate = r -> true;
        StringBuilder sb = new StringBuilder("Added filters: ");

        if (requestDTO.getRestaurantName() != null) {
            predicate = predicate.and(FilterLogic.NAME.build(requestDTO.getRestaurantName()));
            sb.append("Name |");
        }

        if (requestDTO.getCuisineName() != null) {
            predicate = predicate.and(FilterLogic.CUISINE.build(requestDTO.getCuisineName()));
            sb.append("Cuisine Name |");
        }

        if (requestDTO.getPrice() != null) {
            predicate = predicate.and(FilterLogic.PRICE.build(requestDTO.getPrice()));
            sb.append("Price |");
        }

        if (requestDTO.getCustomerRating() != null) {
            predicate = predicate.and(FilterLogic.CUSTOMER_RATING.build(requestDTO.getCustomerRating()));
            sb.append("Customer Rating |");
        }

        if (requestDTO.getDistance() != null) {
            predicate = predicate.and(FilterLogic.DISTANCE.build(requestDTO.getDistance()));
            sb.append("Distance");
        }

        log.info(sb.toString());
        return predicate;
    }
}
