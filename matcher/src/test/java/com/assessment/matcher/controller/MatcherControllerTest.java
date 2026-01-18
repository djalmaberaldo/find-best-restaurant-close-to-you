package com.assessment.matcher.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MatcherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllRestaurants() throws Exception {
        mockMvc.perform(get("/api/restaurants").with(user("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[*].name").isNotEmpty())
                .andExpect(jsonPath("$[*].cuisineName").isNotEmpty())
                .andExpect(jsonPath("$[*].priceSinglePersonSpent").exists())
                .andExpect(jsonPath("$[*].distance").exists())
                .andExpect(jsonPath("$[*].customerRating").exists());
    }

    @Test
    void shouldReturnAllRestaurantsCuisineNameEquals() throws Exception {
        mockMvc.perform(get("/api/restaurants")
                        .param("cuisineName", "Italian")
                        .with(user("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[*].cuisineName", everyItem(containsString("Italian"))));
    }

    @Test
    void shouldReturnAllRestaurantsPartialCuisineNameEquals() throws Exception {
        mockMvc.perform(get("/api/restaurants")
                        .param("cuisineName", "Ital")
                        .with(user("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[*].cuisineName", everyItem(containsString("Italian"))));
    }

    @Test
    void shouldReturnAllRestaurantsPartialRestaurantNameEquals() throws Exception {
        mockMvc.perform(get("/api/restaurants")
                        .param("name", "Delicious")
                        .with(user("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[*].name", everyItem(containsStringIgnoringCase("Delicious"))));
    }

    @Test
    void shouldReturnAllRestaurantsDistanceMatching() throws Exception {
        mockMvc.perform(get("/api/restaurants")
                        .param("distance", "3")
                        .with(user("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[*].distance", everyItem(lessThanOrEqualTo((3)))));
    }

    @Test
    void shouldReturnAllRestaurantsPriceMatching() throws Exception {
        mockMvc.perform(get("/api/restaurants")
                        .param("price", "20")
                        .with(user("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[*].priceSinglePersonSpent", everyItem(lessThanOrEqualTo((20)))));
    }

    @Test
    void shouldReturnAllRestaurantsPriceCuisineDistanceMatching() throws Exception {
        mockMvc.perform(get("/api/restaurants")
                        .param("price", "20")
                        .param("distance", "3")
                        .param("cuisineName", "Italian")
                        .with(user("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessThanOrEqualTo(5)))
                .andExpect(jsonPath("$[*].priceSinglePersonSpent", everyItem(lessThanOrEqualTo((20)))))
                .andExpect(jsonPath("$[*].distance", everyItem(lessThanOrEqualTo((3)))))
                .andExpect(jsonPath("$[*].cuisineName", everyItem(containsString("Italian"))));
    }

    @Test
    void shouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/restaurants")
                        .param("price", "20")
                        .param("distance", "3")
                        .param("cuisineName", "XXXXX")
                        .with(user("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(lessThanOrEqualTo(0)));

    }

}