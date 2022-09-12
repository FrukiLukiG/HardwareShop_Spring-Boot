package hr.tvz.plese.hardwareapp.review_packages.review_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testIfDatabaseReturnsReviews() throws Exception {
        String JWToken = getJWT();

        this.mockMvc.perform(
                get("/review")
                        .header("Authorization", "Bearer " + JWToken)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(21)));
    }

    @Test
    void testIfDatabaseReturnsReviewByHardwareCode() throws Exception{
        String JWToken = getJWT();
        String hwCode = "4053156801";

        this.mockMvc.perform(
                get("/review/?hardwareCode=" + hwCode)
                        .header("Authorization", "Bearer " + JWToken)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testIfDatabaseDoesNotReturnReviewByFakeHardwareCode() throws Exception{
        String JWToken = getJWT();
        String hwCode = "FAKE_CODE";

        this.mockMvc.perform(
                get("/review/?hardwareCode=" + hwCode)
                        .header("Authorization", "Bearer " + JWToken)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    private String getJWT() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("username", "adminUser");
        body.put("password", "admin");

        String response = mockMvc.perform(
                post("/authentication/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.jwt").isNotEmpty())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return response.replace("{\"jwt\":\"", "").replace("\"}", "");
    }

}