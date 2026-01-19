package com.assessment.matcher.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class RequestDTO {
    private String restaurantName;

    @Min(value = 1, message = "Distance value should be at least 1")
    @Max(value = 10, message = "Distance value should be at most 10")
    private Integer distance;


    @Min(value = 10, message = "Price value should be at least 10")
    @Max(value = 50, message = "Price value should be at most 50")
    private Integer price;

    @Min(value = 1, message = "Rating value should be at least 1")
    @Max(value = 5, message = "Rating value should be at most 5")
    private Integer customerRating;

    private String cuisineName;
}
