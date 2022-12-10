package be.ecam.ms_studenthelp.PostUnitTest;


import be.ecam.ms_studenthelp.CategoriesUnitTest.CreateCategoriesForTests;
import be.ecam.ms_studenthelp.CategoriesUnitTest.DeleteCategoriesForTests;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateThreadTestForPosts {
    public static String createThreadTestForPosts(MockMvc mockMvc, int port) throws Exception {

        String json = "{\"title\":\"Test thread\",\"category\":\"TestUnit\",\"tags\":[],\"firstPost\":{\"authorId\":\"d66b3f8c-2271-4afb-a348-e370effff\",\"content\":\"Test post\"}}";

        MvcResult result = mockMvc.perform(post("http://localhost:" + port + "/threads/")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> idResponse = springParser.parseMap(response.getContentAsString());
        return (String) idResponse.get("firstPost");
    }
}

