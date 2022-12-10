package be.ecam.ms_studenthelp.PostUnitTest;


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
class UnitTestPatchPostContent {
    @Value("${server.port}")
    private int port;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unitTestPatchPostContent() throws Exception {
        String IdPost = CreateThreadTestForPosts.createThreadTestForPosts(mockMvc, port);

        String json = "{\"content\":\"Update\",\"authorId\":\"d66b3f8c-2271-4afb-a348-e370ef9990\"}";
        this.mockMvc.perform((patch("http://localhost:" + port + "/posts/" + IdPost)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));

        this.mockMvc.perform(get("http://localhost:" + port + "/posts/" + IdPost))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("Update")));
    }
}


