package com.assessment.matcher.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
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
    void shouldReturnAllRestaurantsSortedByCustomerRating() throws Exception {
        mockMvc.perform(get("/api/top-rated").with(user("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].cuisineName").isNotEmpty())
                .andExpect(jsonPath("$[0].priceSinglePersonSpent").exists())
                .andExpect(jsonPath("$[0].distance").exists())
                .andExpect(jsonPath("$[0].customerRating").exists())

                .andExpect(jsonPath("$[1].name").isNotEmpty())
                .andExpect(jsonPath("$[1].cuisineName").isNotEmpty())
                .andExpect(jsonPath("$[1].priceSinglePersonSpent").exists())
                .andExpect(jsonPath("$[1].distance").exists())
                .andExpect(jsonPath("$[1].customerRating").exists())

                .andExpect(jsonPath("$[2].name").isNotEmpty())
                .andExpect(jsonPath("$[2].cuisineName").isNotEmpty())
                .andExpect(jsonPath("$[2].priceSinglePersonSpent").exists())
                .andExpect(jsonPath("$[2].distance").exists())
                .andExpect(jsonPath("$[2].customerRating").exists())

                .andExpect(jsonPath("$[3].name").isNotEmpty())
                .andExpect(jsonPath("$[3].cuisineName").isNotEmpty())
                .andExpect(jsonPath("$[3].priceSinglePersonSpent").exists())
                .andExpect(jsonPath("$[3].distance").exists())
                .andExpect(jsonPath("$[3].customerRating").exists())

                .andExpect(jsonPath("$[4].name").isNotEmpty())
                .andExpect(jsonPath("$[4].cuisineName").isNotEmpty())
                .andExpect(jsonPath("$[4].priceSinglePersonSpent").exists())
                .andExpect(jsonPath("$[4].distance").exists())
                .andExpect(jsonPath("$[4].customerRating").exists());

    }

}