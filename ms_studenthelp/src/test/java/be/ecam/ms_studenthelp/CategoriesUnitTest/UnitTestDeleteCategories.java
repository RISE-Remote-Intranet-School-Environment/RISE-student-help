package be.ecam.ms_studenthelp.CategoriesUnitTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestDeleteCategories {

    @Value("${server.port}")
    private int port;
    @Autowired
    private MockMvc mockMvc;

    UnitTestDeleteCategories() {
    }

    @Test
    public void unitTestDeleteCategories() throws Exception {
            String json = "{\"title\":\"NeedToBeDeletedUnitTest\"}";

            mockMvc.perform(post("http://localhost:" + port + "/categories/")
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            mockMvc.perform(delete("http://localhost:" + port + "/categories/NeedToBeDeletedUnitTest")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
    }


