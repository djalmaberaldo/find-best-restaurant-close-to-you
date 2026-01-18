package com.assessment.matcher.domain;

import com.assessment.matcher.domain.dto.RestaurantDTO;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public enum FilterLogic {

    NAME(3, (restaurant,value) -> restaurant.getName().contains((String) value)),
    CUISINE(4, (restaurant,value) -> restaurant.getCuisineName().contains((String) value)),
    PRICE (2, (restaurant,value) -> restaurant.getPriceSinglePersonSpent() <=  (int) value),
    CUSTOMER_RATING(5, (restaurant,value) -> restaurant.getCustomerRating() >  (int) value),
    DISTANCE(1, (restaurant,value) -> restaurant.getDistance() <=  (int)value);

    private final int tieBreakerRanking;
    private final BiPredicate<RestaurantDTO, Object> predicate;

    FilterLogic(int tieBreakerRanking, BiPredicate<RestaurantDTO, Object> predicate) {
        this.tieBreakerRanking = tieBreakerRanking;
        this.predicate = predicate;
    }

    public Predicate<RestaurantDTO> build(Object value) {
        return r -> predicate.test(r, value);
    }
}
