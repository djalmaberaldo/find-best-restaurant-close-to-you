package com.assessment.matcher.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDTO {
    private String restaurantName;
    private Integer distance;
    private Integer price;
    private Integer customerRating;
    private String cuisineName;
}
