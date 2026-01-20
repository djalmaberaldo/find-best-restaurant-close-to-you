package com.assessment.matcher.filters;

import com.assessment.matcher.domain.dto.ResponseDTO;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import lombok.Getter;

@Getter
public enum FilterLogic {

    NAME(
            (restaurant,value) -> restaurant.getRestaurantName().toLowerCase().contains((String.valueOf(value).toLowerCase())),
            (r1, r2) -> r1.getRestaurantName().compareTo(r2.getRestaurantName())),
    CUISINE(
            (restaurant,value) -> restaurant.getCuisineName().contains((String) value),
            (r1, r2) -> r1.getCuisineName().compareTo(r2.getCuisineName())),
    PRICE (
            (restaurant,value) -> restaurant.getPriceSinglePersonSpent() <=  (int) value,
            (r1, r2) -> r1.getPriceSinglePersonSpent().compareTo(r2.getPriceSinglePersonSpent())),
    CUSTOMER_RATING(
            (restaurant,value) -> restaurant.getCustomerRating() >  (int) value,
            (r1, r2) -> r2.getCustomerRating().compareTo(r1.getCustomerRating())),
    DISTANCE(
            (restaurant,value) -> restaurant.getDistance() <=  (int)value,
            (r1, r2) -> r1.getDistance().compareTo(r2.getDistance()));

    private final BiPredicate<ResponseDTO, Object> predicate;
    private final Comparator<ResponseDTO> comparator;

    FilterLogic(BiPredicate<ResponseDTO, Object> predicate, Comparator<ResponseDTO> comparator) {
        this.predicate = predicate;
        this.comparator = comparator;
    }

    public Predicate<ResponseDTO> build(String value) {
        return r -> predicate.test(r, value);
    }

    public Predicate<ResponseDTO> build(Integer value) {
        return r -> predicate.test(r, value);
    }

}
