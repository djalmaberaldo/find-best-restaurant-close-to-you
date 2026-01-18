package com.assessment.matcher.domain.dto;

import java.util.List;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class RequestDTO {
    private String restaurantName;
    private Integer distance;
    private Integer price;
    private Integer customerRating;
    private String cuisineName;
}
