package com.assessment.matcher.domain.parameters;


import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.RestaurantDTO;
import org.springframework.stereotype.Component;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Component
public class ParametersMapper {

    public static Predicate<RestaurantDTO> getValidFilters (RequestDTO requestDTO) {

        Predicate<RestaurantDTO> predicate = r -> true;

        if (requestDTO.restaurantName() != null) {
            predicate = predicate.and(Filter.NAME.build(requestDTO.restaurantName()));
        }

        if (requestDTO.cuisineName() != null) {
            predicate = predicate.and(Filter.CUISINE.build(requestDTO.cuisineName()));
        }

        if (requestDTO.price() != null) {
            predicate = predicate.and(Filter.PRICE.build(requestDTO.price()));
        }

        if (requestDTO.customerRating() != null) {
            predicate = predicate.and(Filter.CUSTOMER_RATING.build(requestDTO.customerRating()));
        }

        if (requestDTO.distance() != null) {
            predicate = predicate.and(Filter.DISTANCE.build(requestDTO.distance()));
        }

        return predicate;
    }

    enum Filter {

        NAME(3, (restaurant,value) -> restaurant.getName().contains((String) value)),
        CUISINE(4, (restaurant,value) -> restaurant.getCuisineName().contains((String) value)),
        PRICE (2, (restaurant,value) -> restaurant.getPriceSinglePersonSpent() <  (int) value),
        CUSTOMER_RATING(5, (restaurant,value) -> restaurant.getCustomerRating() >  (int) value),
        DISTANCE(1, (restaurant,value) -> restaurant.getDistance() <  (int)value);

        private final int tieBreakerRanking;
        private final BiPredicate<RestaurantDTO, Object> predicate;

        Filter(int tieBreakerRanking, BiPredicate<RestaurantDTO, Object> predicate) {
            this.tieBreakerRanking = tieBreakerRanking;
            this.predicate = predicate;
        }

        public Predicate<RestaurantDTO> build(Object value) {
            return r -> predicate.test(r, value);
        }
    }
}
