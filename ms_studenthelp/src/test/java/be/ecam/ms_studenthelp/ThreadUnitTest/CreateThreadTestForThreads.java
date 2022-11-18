package be.ecam.ms_studenthelp.ThreadUnitTest;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateThreadTestForThreads {
    public static String createThreadTestForThreads(MockMvc mockMvc, int port) throws Exception {

        String json = "{\"title\":\"Test thread\",\"category\":\"Math\",\"tags\":[],\"firstPost\":{\"authorId\":\"d66b3f8c-2271-4afb-a348-e370effff\",\"content\":\"First post\"}}";

        MvcResult result = mockMvc.perform(post("http://localhost:" + port + "/threads/")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> idResponse = springParser.parseMap(response.getContentAsString());
        System.out.println(idResponse);
        return (String) idResponse.get("id");
    }
}
