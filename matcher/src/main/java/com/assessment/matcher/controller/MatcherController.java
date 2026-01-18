package com.assessment.matcher.controller;

import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.RestaurantDTO;
import com.assessment.matcher.service.MatcherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MatcherController {

    public final MatcherService matcherService;

    public MatcherController(MatcherService matcherService) {
        this.matcherService = matcherService;
    }

    @GetMapping("/restaurants")
    public List<RestaurantDTO> find(
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) Integer distance,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) Integer customerRating,
            @RequestParam(required = false) String cuisineName
    ) {

        return matcherService.findBestRestaurants(new RequestDTO(
                restaurantName,
                distance,
                price,
                customerRating,
                cuisineName
        ));
    }
}
