package be.ecam.ms_studenthelp;

import static org.hamcrest.CoreMatchers.is;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MsStudenthelpApplicationTests{

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void UnitTestCheckDBStatus() throws Exception {
		mockMvc.perform(get("http://localhost:8080/categories"))
				.andExpect(status().isOk());
	}
	@Test
	public void UnitTestThreadsCreateContent() throws Exception {
		/*
		Création et envoie d'un Thread
		 */
		String jos = "{\"title\":\"Test thread\",\"category\":\"Math\",\"tags\":[],\"firstPost\":{\"authorId\":\"d66b3f8c-2271-4afb-a348-e370effff\",\"content\":\"Fisrt postman\"}}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jos.toString(),headers);
		this.restTemplate.postForObject("http://localhost:" + 8080 + "/threads",entity, String.class);
		/*
		1. Demander Récuperer ID
		2. Demander ou mise en forme et possibilité récuperer pour comparer résultat
		*/
		this.mockMvc.perform(get("http://localhost:" + 8080 + "/threads"+ "/4186627e-aa7e-4ee9-b303-9d06d8db44be"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", is("Test thread")));
		/*.andExpect(content().json("{\"id\":\"4186627e-aa7e-4ee9-b303-9d06d8db44be\",\"title\":\"Test thread\",\"answered\":false,\"category\":\"Math\",\"datePosted\":\"2022-11-15T19:34:42\",\"dateModified\":\"2022-11-15T19:34:42\",\"firstPost\":\"7a7b64ba-f49b-476c-9fb6-7a887ad1cee6\",\"tags\":[]}\"")));
	*/};
	}




