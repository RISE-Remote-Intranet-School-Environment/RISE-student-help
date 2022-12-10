package be.ecam.ms_studenthelp.ThreadUnitTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UnitTestThreadsUpdateContent {
    @Value("${server.port}")
    private int port;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unitTestThreadsUpdateContent() throws Exception {
        String id = CreateThreadTestForThreads.createThreadTestForThreads(mockMvc, port);

        String json = "{\"title\":\"Test Update\",\"category\":\"UnitTest\",\"tags\":[],\"firstPost\":{\"authorId\":\"d66b3f8eac-2271-4afb-a348-e370effff\",\"content\":\"Fist Post\"}}";
        this.mockMvc.perform((patch("http://localhost:" + port + "/threads/" + id)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));

        this.mockMvc.perform(get("http://localhost:" + port + "/threads/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Update")));
    }
}
