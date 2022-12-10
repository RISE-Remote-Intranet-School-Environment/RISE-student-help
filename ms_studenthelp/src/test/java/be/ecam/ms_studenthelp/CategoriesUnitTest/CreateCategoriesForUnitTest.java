package be.ecam.ms_studenthelp.CategoriesUnitTest;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateCategoriesForUnitTest {
    public static void createCategoriesForUnitTest(MockMvc mockMvc, int port) throws Exception {
        MvcResult result = mockMvc.perform(get("http://localhost:" + port + "/categories"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String responseString = response.getContentAsString();

        String output = responseString.replaceAll("[\\[\\]]", "");
        String newString = output.replace("\"", "");
        String[] elements = newString.split(",");

        ArrayList<String> Checking = new ArrayList<>();

        Collections.addAll(Checking, elements);
        if (Checking.contains("UnitTest")) {}
        else
        {
            String json = "{\"title\":\"UnitTest\"}";
            mockMvc.perform(post("http://localhost:" + port + "/categories/")
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk());
        }
    }
}

