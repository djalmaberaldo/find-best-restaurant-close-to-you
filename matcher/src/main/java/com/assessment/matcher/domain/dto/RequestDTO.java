package com.assessment.matcher.domain.dto;

import java.util.List;
import java.util.function.Predicate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Min(value = 1)
    @Max(value = 10)
    private Integer distance;

    private Integer price;

    @Min(value = 1)
    @Max(value = 5)
    private Integer customerRating;

    private String cuisineName;
}
