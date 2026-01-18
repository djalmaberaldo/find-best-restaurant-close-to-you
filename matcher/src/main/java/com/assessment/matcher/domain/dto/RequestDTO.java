package com.assessment.matcher.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class RequestDTO {
    private String restaurantName;
    private Integer distance;
    private Integer price;
    private Integer customerRating;
    private String cuisineName;

}
