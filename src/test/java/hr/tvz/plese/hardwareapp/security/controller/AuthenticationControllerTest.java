package hr.tvz.plese.hardwareapp.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.plese.hardwareapp.enumerations.TypeEnum;
import hr.tvz.plese.hardwareapp.hardware_packages.hardware_controller.HardwareCommand;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testIfLoginSuccessful() throws Exception{
        Map<String, Object> body = new HashMap<>();
        body.put("username", "adminUser");
        body.put("password", "admin");

        mockMvc.perform(post("/authentication/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.jwt").isNotEmpty());
    }

    @Test
    @Transactional
    void testIfLoginSuccessfulAndHardwareInserted() throws Exception{
        HardwareCommand testHardware  = new HardwareCommand();
        testHardware.setCode("1234567890");
        testHardware.setName("TestRamHardware");
        testHardware.setType(TypeEnum.RAM);
        testHardware.setPrice(99.99);
        testHardware.setStock(2);


        mockMvc.perform(post("/hardware")
                        .with(user("adminUser").password("admin").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(testHardware))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().encoding(StandardCharsets.UTF_8))
                .andExpect(jsonPath("$.code").value("1234567890"))
                .andExpect(jsonPath("$.name").value("TestRamHardware"))
                .andExpect(jsonPath("$.price").value(99.99));
    }

}