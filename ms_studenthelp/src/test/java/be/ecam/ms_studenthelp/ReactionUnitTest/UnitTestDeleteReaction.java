package be.ecam.ms_studenthelp.ReactionUnitTest;

import be.ecam.ms_studenthelp.PostUnitTest.CreateThreadTestForPosts;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestGetReaction {
    private final int port = 8080;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void unitTestGetReaction() throws Exception {
        String IdPost = CreateThreadTestForPosts.createThreadTestForPosts(mockMvc, port);



        MvcResult answer = mockMvc.perform(get("http://localhost:" + port + "/posts/" + IdPost + "/reactions"))
                .andReturn();


        MockHttpServletResponse response = answer.getResponse();
        JsonParser springParser = JsonParserFactory.getJsonParser();
        List<Object> responseList = springParser.parseList(response.getContentAsString());

        Assert.assertEquals((int) ((Map<String, Object>) responseList.get(0)).get("value"),0);
    }
}




