package be.ecam.ms_studenthelp.PostUnitTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestGetPostContent {
    private final int port = 8080;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unitTestGetPostContent() throws Exception {
        String IdPost = CreateThreadTestForPosts.createThreadTestForPosts(mockMvc, port);

        this.mockMvc.perform(get("http://localhost:" + port + "/posts/" + IdPost))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("Test post")));
    }
}


