package com.assessment.matcher.controller;

import com.assessment.matcher.domain.dto.RequestDTO;
import com.assessment.matcher.domain.dto.ResponseDTO;
import com.assessment.matcher.service.MatcherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class MatcherController {

    public final MatcherService matcherService;

    public MatcherController(MatcherService matcherService) {
        this.matcherService = matcherService;
    }

    @GetMapping("/restaurants")
    public List<ResponseDTO> find(@Valid RequestDTO requestDTO){
        log.info("Received request: {}", requestDTO);

        return matcherService.findBestRestaurants(requestDTO);
    }
}
