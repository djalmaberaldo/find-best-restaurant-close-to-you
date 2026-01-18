package com.assessment.matcher.service;

import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.RestaurantDTO;
import com.assessment.matcher.domain.mapper.RestaurantMapper;
import com.assessment.matcher.filters.FilterLogic;
import com.assessment.matcher.filters.ParametersFilter;
import com.assessment.matcher.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.Collections;
import java.util.List;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MatcherService {

    public final RestaurantRepository restaurantRepository;
    public final ParametersFilter parametersFilter;

    public static int MAX_RESTAURANTS = 5;

    public MatcherService(RestaurantRepository restaurantRepository,
                          ParametersFilter parametersFilter) {
        this.restaurantRepository = restaurantRepository;
        this.parametersFilter = parametersFilter;
    }

    public List<RestaurantDTO> findBestRestaurants(@Validated @ModelAttribute RequestDTO requestDTO) {

        List<RestaurantDTO> result = restaurantRepository.findAll().stream()
                .map(RestaurantMapper::toDTO)
                .filter(parametersFilter.getValidFiltersFromRequest(requestDTO))
                .sorted(FilterLogic.DISTANCE.getComparator()
                        .thenComparing(FilterLogic.CUSTOMER_RATING.getComparator())
                        .thenComparing(FilterLogic.PRICE.getComparator())
                        .thenComparing(FilterLogic.NAME.getComparator()))
                .toList();

        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.subList(0, Math.min(result.size(), MAX_RESTAURANTS));
    }
}
