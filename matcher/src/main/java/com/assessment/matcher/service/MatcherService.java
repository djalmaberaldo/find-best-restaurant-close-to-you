package com.assessment.matcher.service;

import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.RestaurantDTO;
import com.assessment.matcher.domain.mapper.RestaurantMapper;
import com.assessment.matcher.domain.parameters.ParametersMapper;
import com.assessment.matcher.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatcherService {

    public final RestaurantRepository restaurantRepository;

    public MatcherService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantDTO> findBestRestaurants(RequestDTO requestDTO) {
        return restaurantRepository.findAll().stream()
                .map(RestaurantMapper::toDTO)
                .filter(ParametersMapper.getValidFilters(requestDTO))
                .toList()
                .subList(0, 5);
    }
}
