package com.assessment.matcher.filters;

import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class ParametersFilterTest {

    private ParametersFilter parametersFilter;

    private ResponseDTO restaurant;

    @BeforeEach
    void setup() {
        parametersFilter = new ParametersFilter();

        restaurant = new ResponseDTO(
                "Pizza Place",
                "Italian",
                30,
                5,
                3
        );
    }

    @Test
    void shouldAcceptAllWhenNoFiltersProvided() {
        RequestDTO request = RequestDTO.builder().build();

        Predicate<ResponseDTO> predicate =
                parametersFilter.getValidFiltersFromRequest(request);

        assertThat(predicate.test(restaurant)).isTrue();
    }

    @Test
    void shouldFilterByName() {
        RequestDTO request = RequestDTO.builder().restaurantName("Pizza").build();

        Predicate<ResponseDTO> predicate =
                parametersFilter.getValidFiltersFromRequest(request);

        assertThat(predicate.test(restaurant)).isTrue();
    }

    @Test
    void shouldFilterByCuisine() {
        RequestDTO request = RequestDTO.builder().cuisineName("Italian").build();

        Predicate<ResponseDTO> predicate =
                parametersFilter.getValidFiltersFromRequest(request);

        assertThat(predicate.test(restaurant)).isTrue();
    }

    @Test
    void shouldFilterByPrice() {
        RequestDTO request = RequestDTO.builder().price(40).build();

        Predicate<ResponseDTO> predicate =
                parametersFilter.getValidFiltersFromRequest(request);

        assertThat(predicate.test(restaurant)).isTrue();
    }

    @Test
    void shouldRejectByPrice() {
        RequestDTO request = RequestDTO.builder().price(20).build();

        Predicate<ResponseDTO> predicate =
                parametersFilter.getValidFiltersFromRequest(request);

        assertThat(predicate.test(restaurant)).isFalse();
    }

    @Test
    void shouldFilterByCustomerRating() {
        RequestDTO request = RequestDTO.builder().distance(4).build();

        Predicate<ResponseDTO> predicate =
                parametersFilter.getValidFiltersFromRequest(request);

        assertThat(predicate.test(restaurant)).isTrue();
    }

    @Test
    void shouldFilterByDistance() {
        RequestDTO request = RequestDTO.builder().distance(5).build();

        Predicate<ResponseDTO> predicate =
                parametersFilter.getValidFiltersFromRequest(request);

        assertThat(predicate.test(restaurant)).isTrue();
    }

    @Test
    void shouldApplyMultipleFiltersTogether() {
        RequestDTO request = RequestDTO.builder().restaurantName("Pizza")
                .distance(5).price(40).customerRating(4).cuisineName("Italian")
                .build();

        Predicate<ResponseDTO> predicate =
                parametersFilter.getValidFiltersFromRequest(request);

        assertThat(predicate.test(restaurant)).isTrue();
    }

    @Test
    void shouldRejectWhenAnyFilterFails() {
        RequestDTO request = RequestDTO.builder().restaurantName("Burger")
                .distance(1).price(10).customerRating(1).cuisineName("Mexican")
                .build();

        Predicate<ResponseDTO> predicate =
                parametersFilter.getValidFiltersFromRequest(request);

        assertThat(predicate.test(restaurant)).isFalse();
    }
}
