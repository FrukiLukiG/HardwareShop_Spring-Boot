package hr.tvz.plese.hardwareapp.hardware_packages.hardware_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.plese.hardwareapp.enumerations.TypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testIfDatabaseReturnsHardware() throws Exception {
        String JWToken = getJWT();

        this.mockMvc.perform(
                get("/hardware")
                        .header("Authorization", "Bearer " + JWToken)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(12)));
    }

    @Test
    public void testIfDatabaseReturnsHardwareByCode() throws Exception {
        String JWToken = getJWT();
        String hwCode = "4053156801";

        this.mockMvc.perform(
                get("/hardware/" + hwCode)
                        .header("Authorization", "Bearer " + JWToken)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", aMapWithSize(3)));
    }

    @Test
    void testIfDatabaseDoesNotReturnHardwareByFakeCode() throws Exception {
        String JWToken = getJWT();
        String hwCode = "FAKE_CODE";

        this.mockMvc.perform(
                get("/hardware/" + hwCode)
                        .header("Authorization", "Bearer " + JWToken)
                        .with(csrf())
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testIfHardwareIsSavedInDatabase() throws Exception {
        String JWToken = getJWT();

        Map<String, Object> hardwareToInsert = new HashMap<>();
        hardwareToInsert.put("name", "Test Hardware");
        hardwareToInsert.put("code", "9999999999");
        hardwareToInsert.put("type", TypeEnum.OTHER.toString());
        hardwareToInsert.put("price", "99.99");
        hardwareToInsert.put("stock", "123");

        this.mockMvc.perform(
                post("/hardware")
                        .header("Authorization", "Bearer " + JWToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hardwareToInsert))
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", aMapWithSize(3)));
    }

    @Test
    @Transactional
    public void testIfHardwareIsUpdatedInDatabase() throws Exception {
        String JWToken = getJWT();
        String hwCode = "9749230089";

        Map<String, Object> hardwareAfterUpdate = new HashMap<>();
        hardwareAfterUpdate.put("name", "Samsung LC32G55 monitor");
        hardwareAfterUpdate.put("code", "9749230089");
        hardwareAfterUpdate.put("type", TypeEnum.OTHER.toString());
        hardwareAfterUpdate.put("price", "999.99");
        hardwareAfterUpdate.put("stock", "89");

        this.mockMvc.perform(
                put("/hardware/" + hwCode)
                        .header("Authorization", "Bearer " + JWToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hardwareAfterUpdate))
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", aMapWithSize(3)));
    }

    @Test
    @Transactional
    public void testIfHardwareIsDeletedFromDatabase() throws Exception {
        String JWToken = getJWT();
        String hwCode = "9749230089";

        this.mockMvc.perform(
                delete("/hardware/" + hwCode)
                        .header("Authorization", "Bearer " + JWToken)
                        .with(csrf())
                )
                .andExpect(status().isNoContent());
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