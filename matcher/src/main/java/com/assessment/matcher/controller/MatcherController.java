package com.assessment.matcher.controller;

import com.assessment.matcher.domain.dto.RestaurantDTO;
import com.assessment.matcher.domain.mapper.RestaurantMapper;
import com.assessment.matcher.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MatcherController {

    public final RestaurantRepository restaurantRepository;

    public MatcherController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/top-rated")
    public List<RestaurantDTO> find() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantMapper::toDTO)
                .sorted(Comparator.comparing(RestaurantDTO::customerRating).reversed())
                .limit(5)
                .toList();
    }
}
