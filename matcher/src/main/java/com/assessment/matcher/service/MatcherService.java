package com.assessment.matcher.service;

import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.ResponseDTO;
import com.assessment.matcher.domain.mapper.RestaurantMapper;
import com.assessment.matcher.filters.ParametersFilter;
import com.assessment.matcher.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import static com.assessment.matcher.filters.FilterLogic.CUSTOMER_RATING;
import static com.assessment.matcher.filters.FilterLogic.DISTANCE;
import static com.assessment.matcher.filters.FilterLogic.NAME;
import static com.assessment.matcher.filters.FilterLogic.PRICE;

@Slf4j
@Service
public class MatcherService {

    public final RestaurantRepository restaurantRepository;

    public static int MAX_RESTAURANTS = 5;

    public MatcherService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<ResponseDTO> findBestRestaurants(RequestDTO requestDTO) {

        Predicate<ResponseDTO> filters = ParametersFilter.getValidFiltersFromRequest(requestDTO);

        Comparator<ResponseDTO> ranking = DISTANCE.getComparator()
                        .thenComparing(CUSTOMER_RATING.getComparator())
                        .thenComparing(PRICE.getComparator())
                        .thenComparing(NAME.getComparator());

        log.info("Ranking was set");

        List<ResponseDTO> result = restaurantRepository.findAll().stream()
                .map(RestaurantMapper::toDTO)
                .filter(filters)
                .sorted(ranking)
                .toList();

        if (result.isEmpty()) {
            log.info("No matches were found");
            return Collections.emptyList();
        }

        log.info("Returning best results");
        return result.subList(0, Math.min(result.size(), MAX_RESTAURANTS));
    }
}
