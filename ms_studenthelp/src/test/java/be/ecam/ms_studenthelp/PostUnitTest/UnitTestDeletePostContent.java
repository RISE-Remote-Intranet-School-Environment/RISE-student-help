package be.ecam.ms_studenthelp.PostUnitTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestDeletePostContent {
    @Value("${server.port}")
    private int port;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unitTestDeletePostContent() throws Exception {
        String IdPost = CreateThreadTestForPosts.createThreadTestForPosts(mockMvc, port);

        String json = "{\"content\":\"Reply to first post\",\"authorId\":\"d66b3f8c-2271-4afb-a348-e370ef9990\"}";
        MvcResult result = this.mockMvc.perform((put("http://localhost:" + port + "/posts/" + IdPost + "/answers")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)))
                        .andReturn();

        MockHttpServletResponse response = result.getResponse();
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> idResponse = springParser.parseMap(response.getContentAsString());
        String replyFirstPost = (String) idResponse.get("id");

        this.mockMvc.perform(get("http://localhost:" + port + "/posts/" + replyFirstPost))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("Reply to first post")));

        this.mockMvc.perform(delete("http://localhost:" + port + "/posts/" + replyFirstPost))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("http://localhost:" + port + "/posts/" + replyFirstPost))
                .andExpect(status().isNotFound());

    }
}
