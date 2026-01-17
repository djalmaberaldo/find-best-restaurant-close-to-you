package com.assessment.matcher.domain.dto;

import lombok.Builder;


@Builder
public record RestaurantDTO(
        String name,
        String cuisineName,
        Integer priceSinglePersonSpent,
        Integer customerRating,
        Integer distance
) {
}
