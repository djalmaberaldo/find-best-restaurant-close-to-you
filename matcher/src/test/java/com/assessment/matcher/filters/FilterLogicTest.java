package com.assessment.matcher.filters;

import com.assessment.matcher.domain.dto.ResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class FilterLogicTest {

    private final ResponseDTO pizza = new ResponseDTO(
            "Pizza Place", "Italian", 30, 5, 3
    );

    private final ResponseDTO burger = new ResponseDTO(
            "Burger House", "American", 20, 3, 6
    );

    @Test
    void nameFilterMatchesIgnoringCase() {
        Predicate<ResponseDTO> p = FilterLogic.NAME.build("pizza");

        assertThat(p.test(pizza)).isTrue();
        assertThat(p.test(burger)).isFalse();
    }

    @Test
    void cuisineFilterMatches() {
        Predicate<ResponseDTO> p = FilterLogic.CUISINE.build("Italian");

        assertThat(p.test(pizza)).isTrue();
        assertThat(p.test(burger)).isFalse();
    }

    @Test
    void priceFilterAcceptsWhenLowerOrEqual() {
        Predicate<ResponseDTO> p = FilterLogic.PRICE.build(35);

        assertThat(p.test(pizza)).isTrue();
        assertThat(p.test(burger)).isTrue();
    }

    @Test
    void priceFilterRejectsWhenHigher() {
        Predicate<ResponseDTO> p = FilterLogic.PRICE.build(25);

        assertThat(p.test(pizza)).isFalse();
    }

    @Test
    void ratingFilterAcceptsGreaterThan() {
        Predicate<ResponseDTO> p = FilterLogic.CUSTOMER_RATING.build(4);

        assertThat(p.test(pizza)).isTrue();
        assertThat(p.test(burger)).isFalse();
    }

    @Test
    void distanceFilterAcceptsLowerOrEqual() {
        Predicate<ResponseDTO> p = FilterLogic.DISTANCE.build(4);

        assertThat(p.test(pizza)).isTrue();
        assertThat(p.test(burger)).isFalse();
    }

    @Test
    void distanceComparatorSortsAscending() {
        List<ResponseDTO> list = List.of(burger, pizza);

        List<ResponseDTO> sorted =
                list.stream()
                        .sorted(FilterLogic.DISTANCE.getComparator())
                        .toList();

        assertThat(sorted.get(0)).isEqualTo(pizza);
    }

    @Test
    void ratingComparatorSortsDescending() {
        List<ResponseDTO> list = List.of(burger, pizza);

        List<ResponseDTO> sorted =
                list.stream()
                        .sorted(FilterLogic.CUSTOMER_RATING.getComparator())
                        .toList();

        assertThat(sorted.get(0)).isEqualTo(pizza);
    }
}
