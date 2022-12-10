package be.ecam.ms_studenthelp.ReactionUnitTest;

import be.ecam.ms_studenthelp.PostUnitTest.CreateThreadTestForPosts;
import org.junit.Assert;
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
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestUpdateReaction {
    @Value("${server.port}")
    private int port;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unitTestPutReactionContent() throws Exception {
        String IdPost = CreateThreadTestForPosts.createThreadTestForPosts(mockMvc, port);

        String json = "{\"value\":-1,\"authorId\":\"d66b3f8c-2271-4afb-a348-e370ef9990\"}";
        this.mockMvc.perform((put("http://localhost:" + port + "/posts/" + IdPost + "/reactions")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)));

        MvcResult answer = mockMvc.perform(get("http://localhost:" + port + "/posts/" + IdPost + "/reactions"))
                .andReturn();

        MockHttpServletResponse response = answer.getResponse();
        JsonParser springParser = JsonParserFactory.getJsonParser();
        List<Object> responseList = springParser.parseList(response.getContentAsString());

        Assert.assertEquals((int) ((Map<String, Object>) responseList.get(0)).get("value"),-1);
    }
}


