package be.ecam.ms_studenthelp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestCheckDBStatus {

    private final int port = 8080;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unitTestCheckDBStatus() throws Exception {
        this.mockMvc.perform(get("http://localhost:" + port + "/categories"))
                .andExpect(status().isOk());
    }
}

