package com.assessment.matcher.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantDTO {
    private String name;
    private String cuisineName;
    private Integer priceSinglePersonSpent;
    private Integer customerRating;
    private Integer distance;
}
